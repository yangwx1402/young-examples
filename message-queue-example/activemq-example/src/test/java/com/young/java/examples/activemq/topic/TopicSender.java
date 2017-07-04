package com.young.java.examples.activemq.topic;

import com.young.java.examples.activemq.User;
import com.young.java.examples.mq.MessageQueueException;
import com.young.java.examples.topic.MessageTopic;

import java.io.IOException;
import java.util.Properties;

/**
 * Created by yangyong3 on 2017/7/4.
 */
public class TopicSender {
    public static void main(String[] args) throws IOException, MessageQueueException, InterruptedException {

        Properties properties = new Properties();
        properties.load(TopicReceiver.class.getResourceAsStream("/activemq.properties"));
        String name = "topic";
        MessageTopic<User> messageTopic = new ActivemqMessageTopic<>(name,properties);
        for(int i=0;i<10;i++){
            User user = new User();
            user.setAge(i);
            user.setName("yangyong_"+i);
            messageTopic.send(user);
            Thread.sleep(1000);
        }
        messageTopic.destory();
    }
}
