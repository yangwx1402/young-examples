package com.young.java.examples.topic;

import java.util.Properties;

/**
 * Created by yangyong3 on 2017/7/4.
 */
public abstract class AbstractMessageTopic<T> implements MessageTopic<T> {
    protected String queueName;

    protected Properties config;

    public AbstractMessageTopic(String queueName, Properties config) {
        this.queueName = queueName;
        this.config = config;
    }
}
