package com.young.example.spider.distribution.mq.support;

import com.young.example.spider.annotation.ExpensiveOps;
import com.young.example.spider.distribution.mq.MQException;
import com.young.example.spider.distribution.mq.MessageQueue;
import com.young.example.spider.zkclient.ZKClientFactory;
import com.young.example.spider.annotation.NotSupport;
import com.young.example.spider.distribution.mq.AbstractMessageQueue;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.queue.SimpleDistributedQueue;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by yangyong3 on 2017/2/22.
 * 基于zookeeper实现的一个分布式队列
 */
public class ZKSimpleBlockingQueue<T> extends AbstractMessageQueue<T> implements MessageQueue<T> {

    private static final Logger log = LogManager.getLogger(ZKSimpleBlockingQueue.class);

    private static String encode = "utf-8";

    private SimpleDistributedQueue simpleDistributedQueue;

    private CuratorFramework client;

    public ZKSimpleBlockingQueue(String queueName) {
        super(queueName);
        this.client = ZKClientFactory.getZKClient();
        this.simpleDistributedQueue = new SimpleDistributedQueue(this.client, this.queueName);
    }


    @Override
    public void offer(T object) throws MQException {
        checkMessage(object);
        try {
            String json = jsonSerialization.serialization(object);
            simpleDistributedQueue.offer(json.getBytes(encode));
            log.info("offer a object to queue [" + queueName + "]");
        } catch (IOException e) {
            throw new MQException("Serializier [" + object + "] error", e);
        } catch (Exception e) {
            throw new MQException("offer zk queue [" + object + "] error", e);
        }
    }

    @Override
    public void offer(Collection<T> objects) throws MQException {
        checkMessage(objects);
        for (T object : objects)
            offer(object);
    }

    @Override
    public T take(Class<T> tClass) throws MQException {
        try {
            byte[] bytes = simpleDistributedQueue.poll(30, TimeUnit.SECONDS);
            String json = null;
            if (bytes != null) {
                json = new String(bytes, encode);
                T t = jsonSerialization.unserialization(json,tClass);
                log.info("take a object from queue [" + queueName + "] obj is -[" + t + "]");
                return t;
            }
            return null;
        } catch (Exception e) {
            throw new MQException("take tClass = [" + tClass + "] error", e);
        }
    }

    @Override
    public Collection<T> takes(Class<T> tClass, int num) throws MQException {
        List<T> list = new ArrayList<T>();
        if (num <= 0)
            throw new MQException("takes num param must > 0");
        for (int i = 0; i < num; i++) {
            T t = take(tClass);
            if (t != null)
                list.add(take(tClass));
        }
        return list;
    }

    @Override
    @NotSupport
    public long size() throws MQException {
        throw new MQException(ZKSimpleBlockingQueue.class.getName() + ".size() not support");
    }

    @ExpensiveOps
    @Override
    public void clear() throws MQException {
        try {
            List<String> list = client.getChildren().forPath(queueName);
            for (String line : list)
                client.delete().forPath(queueName + "/" + line);
            client.delete().forPath(queueName);
        } catch (Exception e) {
            throw new MQException("delete zk path [" + queueName + "] error", e);
        }
    }

    @Override
    @NotSupport
    public void destory() throws MQException {
        throw new MQException(ZKSimpleBlockingQueue.class.getName() + ".destory() not support");
    }
}
