package com.young.java.examples.activemq.queue;

import com.young.java.examples.activemq.User;
import com.young.java.examples.mq.MessageQueue;
import com.young.java.examples.mq.MessageQueueException;
import com.young.java.examples.activemq.mq.ActivemqMessageQueue;

import java.io.IOException;
import java.util.Properties;

/**
 * Created by yangyong3 on 2017/7/3.
 */
public class MQTest {
    public static void main(String[] args) throws MessageQueueException, IOException {
        Properties properties = new Properties();
        properties.load(MQTest.class.getResourceAsStream("/activemq.properties"));
        String name = "test";
        MessageQueue<User> queue = new ActivemqMessageQueue<User>(name,properties);
        User user = new User();
        user.setAge(20);
        user.setName("123");
        queue.send(user);

        User user2 = queue.receive();
        System.out.println(user2.getName());
        queue.destory();
    }
}
