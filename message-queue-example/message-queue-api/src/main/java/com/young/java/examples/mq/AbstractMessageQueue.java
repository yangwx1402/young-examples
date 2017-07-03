package com.young.java.examples.mq;

/**
 * Created by yangyong3 on 2017/7/3.
 */
public abstract class AbstractMessageQueue<T> implements MessageQueue<T>{
    protected String queueName;

    protected int timeout = 10000;

    protected String url;

    public AbstractMessageQueue(String url, String queueName) {
        this.url = url;
        this.queueName = queueName;
    }

    public AbstractMessageQueue(String queueName, int timeout, String url) {
        this.queueName = queueName;
        this.timeout = timeout;
        this.url = url;
    }
}
