package com.young.java.examples.mq;

import org.apache.log4j.Logger;

import java.io.IOException;

/**
 * Created by yangyong3 on 2016/12/1.
 * 消息队列工厂
 */
public class MessageFactory {

    private MessageQueue queue;

    private static final Logger log = Logger.getLogger(MessageFactory.class);

    public MessageFactory(SpiderMessagePool messagePool) {
        try {
            queue = (MessageQueue) Class.forName(messagePool.getClassname()).newInstance();
            queue.setQueueConfig(messagePool);
            log.info("init message pool classname =[" + messagePool.getClassname() + "],poolsize=[" + messagePool.getPoolSize() + "]");
        } catch (Exception e) {
            e.printStackTrace();
            queue = new BlockingMessageQueue();
            queue.setQueueConfig(messagePool);
            log.error("init message pool error change default messagepool classname =[" + BlockingMessageQueue.class + "],poolsize=[" + 1000 + "]", e);
        }
    }

    public synchronized void pub(String queueName, Object object, MessagePriority priority) throws IOException {
        queue.pub(queueName, object, priority);

    }

    public synchronized <T> T sub(String queueName, Class<T> tClass) throws IOException {
        T t = queue.sub(queueName, tClass);
        return t;

    }

    public synchronized long size(String queueName) throws IOException {
        return queue.size(queueName);
    }

    public synchronized void clear(String queueName) throws IOException {
        queue.clear(queueName);

    }

    public int capacity(String queueName) {
        return queue.capacity();
    }

    public void destory() {
        queue.destory();
    }

}
