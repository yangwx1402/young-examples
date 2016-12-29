package com.young.java.examples.mq;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.util.Hashtable;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * Created by yangyong3 on 2016/11/29.
 * 基于Blockingqueue的消息队列
 */
public class BlockingMessageQueue implements MessageQueue {

    private Map<String, BlockingQueue<String>> mq = new Hashtable<String, BlockingQueue<String>>();

    private static final Logger log = Logger.getLogger(BlockingMessageQueue.class);

    private int queue_size = 1000;

    public synchronized boolean pub(String queueName, Object obj, MessagePriority priority) throws IOException {
        boolean flag = false;
        if (size(queueName) >= capacity())
            return flag;
        if (mq.containsKey(queueName)) {
            flag = mq.get(queueName).offer(JsonUtils.toJson(obj));
        } else {
            BlockingQueue<String> queue = new ArrayBlockingQueue<String>(queue_size);
            flag = queue.offer(JsonUtils.toJson(obj));
            mq.put(queueName, queue);
        }
        log.info("pub now " + queueName + " message pool is -" + size(queueName));
        return flag;
    }

    public synchronized <T> T sub(String queueName, Class<T> tClass) throws IOException {
        if (mq.containsKey(queueName)) {
            String json = mq.get(queueName).poll();
            if (!StringUtils.isBlank(json))
                return JsonUtils.fromJson(json, tClass);
        }
        log.info("sub now " + queueName + " message pool is -" + size(queueName));
        return null;
    }

    public synchronized long size(String queueName) throws IOException {
        if (mq.containsKey(queueName)) {
            return mq.get(queueName).size();
        }
        return 0;
    }

    public void clear(String queueName) throws IOException {
        if (mq.containsKey(queueName)) {
            mq.get(queueName).clear();
        }
        log.info("clear " + queueName + " now size is " + size(queueName));
    }

    public void setQueueConfig(SpiderMessagePool messagePool) {
        this.queue_size = messagePool.getPoolSize();
    }

    public int capacity() {
        return queue_size;
    }

    public void destory() {
        if (mq != null && mq.size() > 0) {
            mq.clear();
        }
    }
}
