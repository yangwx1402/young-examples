package com.young.java.examples.rocketmq;

import com.young.java.examples.mq.MessageQueueException;
import com.young.java.examples.rocketmq.mq.RocketmqMessageQueue;
import com.young.java.examples.topic.MessageTopic;
import com.young.java.examples.topic.TopicMessageCallback;

import java.io.IOException;
import java.util.Properties;

/**
 * Created by yangyong3 on 2017/7/7.
 */
public class RocketmqConsumerTest {

    public static void main(String[] args) throws MessageQueueException, IOException {
        Properties properties = new Properties();
        properties.load(RocketmqMessageQueueExample.class.getResourceAsStream("/rocketmq.properties"));
        MessageTopic<User> messageQueue = new RocketmqMessageQueue<User>("test", properties, false);
        messageQueue.receive(new TopicMessageCallback<User>() {
            @Override
            public void process(User message) throws Exception {
                System.out.println(message.getName());
            }
        });
    }

}
