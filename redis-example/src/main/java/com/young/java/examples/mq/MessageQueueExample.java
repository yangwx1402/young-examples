package com.young.java.examples.mq;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;

import java.io.IOException;

/**
 * Created by yangyong3 on 2016/12/29.
 */
public class MessageQueueExample {
    public static void main(String[] args) throws IOException {
        GenericObjectPoolConfig config = new GenericObjectPoolConfig();
        RedisFactory factory = RedisFactory.getInstance(config, "10.183.222.210", 9008, "sinx/cosx=tanx");
        MessageQueue queue = new PriorityRedisMessageQueue(factory);
        SpiderMessagePool messagePool = new SpiderMessagePool();
        messagePool.setPoolSize(1000);
        queue.setQueueConfig(messagePool);
        String queueName = "test_11111";
        for(int i=0;i<10;i++){
            queue.pub(queueName,"test_"+i,MessagePriority.high);
        }
        for(int i=0;i<10;i++){
            queue.pub(queueName,"test_"+i,MessagePriority.middle);
        }
        for(int i=0;i<10;i++){
            queue.pub(queueName,"test_"+i,MessagePriority.low);
        }
        for(int i=0;i<50;i++){
            System.out.println(queue.sub(queueName,String.class));
        }
    }
}
