package com.young.java.examples.boot;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;

public class OperatorListener implements MessageListener{

    public void onMessage(Message message, byte[] pattern) {

        System.out.println("i get message ="+new String(message.getChannel())+","+new String(message.getBody()));

    }

}
