package com.young.java.examples.activemq;

import com.young.java.examples.mq.MessageQueue;
import com.young.java.examples.mq.MessageQueueException;
import com.young.java.examples.mq.activemq.ActivemqMessageQueue;

/**
 * Created by yangyong3 on 2017/7/3.
 */
public class MQTest {
    public static void main(String[] args) throws MessageQueueException {
        String url = "tcp://10.154.29.223:61616";
        String name = "test";
        MessageQueue<User> queue = new ActivemqMessageQueue<User>(url,name);
        User user = new User();
        user.setAge(20);
        user.setName("123");
        queue.send(user);

        User user2 = queue.receive();
        System.out.println(user2.getName());
    }
}
