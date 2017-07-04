package com.young.java.examples.topic;

/**
 * Created by yangyong3 on 2017/7/4.
 */
public interface TopicMessageCallback<T> {
    void process(T message) throws Exception;
}
