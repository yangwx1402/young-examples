package com.young.java.examples.topic;

import com.young.java.examples.mq.MessageQueueException;

import java.util.Collection;

/**
 * Created by yangyong3 on 2017/7/4.
 */
public interface MessageTopic<T> {

    void send(T message) throws MessageQueueException;

    void send(Collection<T> messages) throws MessageQueueException;

    void receive(TopicMessageCallback<T> callback) throws MessageQueueException;

    void destory() throws MessageQueueException;
}
