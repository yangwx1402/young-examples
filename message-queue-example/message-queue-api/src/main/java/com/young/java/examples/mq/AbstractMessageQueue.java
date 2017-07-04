package com.young.java.examples.mq;

import java.util.Properties;

/**
 * Created by yangyong3 on 2017/7/3.
 */
public abstract class AbstractMessageQueue<T> implements MessageQueue<T>{
    protected String queueName;

    protected Properties config;

    public AbstractMessageQueue(String queueName,Properties properties) {
        this.queueName = queueName;
        this.config = properties;
    }
}
