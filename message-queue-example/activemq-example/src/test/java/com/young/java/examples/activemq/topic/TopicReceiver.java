package com.young.java.examples.activemq.topic;

import com.young.java.examples.activemq.User;
import com.young.java.examples.mq.MessageQueueException;
import com.young.java.examples.topic.MessageTopic;
import com.young.java.examples.topic.TopicMessageCallback;

import java.io.IOException;
import java.util.Properties;

/**
 * Created by yangyong3 on 2017/7/4.
 */
public class TopicReceiver {
    public static void main(String[] args) throws IOException, MessageQueueException {
        Properties properties = new Properties();
        properties.load(TopicReceiver.class.getResourceAsStream("/activemq.properties"));
        String name = "topic";
        MessageTopic<User> messageTopic = new ActivemqMessageTopic<>(name,properties);
        messageTopic.receive(new TopicMessageCallback<User>() {
            @Override
            public void process(User message) throws Exception {
                System.out.println(message.getName());
            }
        });
    }
}
