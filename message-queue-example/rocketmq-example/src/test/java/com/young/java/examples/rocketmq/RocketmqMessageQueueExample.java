package com.young.java.examples.rocketmq;

import com.young.java.examples.mq.MessageQueue;
import com.young.java.examples.mq.MessageQueueException;
import com.young.java.examples.rocketmq.mq.RocketmqMessageQueue;

import java.util.Properties;

/**
 * Created by yangyong3 on 2017/7/6.
 */
public class RocketmqMessageQueueExample {
    public static void main(String[] args) throws MessageQueueException {
        MessageQueue<String> messageQueue = new RocketmqMessageQueue<>("firstQueue",new Properties());
        messageQueue.send("haha");
        messageQueue.destory();
    }
}
