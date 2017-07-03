package com.young.java.examples.mq;

import java.util.Collection;

/**
 * Created by yangyong3 on 2017/7/3.
 */
public interface MessageQueue<T> {
    void send(T t) throws MessageQueueException;

    void send(Collection<T> list) throws MessageQueueException;

    T receive() throws MessageQueueException;

    Collection<T> receive(int size) throws MessageQueueException;
}
