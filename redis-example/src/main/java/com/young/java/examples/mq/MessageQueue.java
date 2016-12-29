package com.young.java.examples.mq;

import java.io.IOException;

/**
 * Created by yangyong3 on 2016/11/29.
 * 消息队列接口，目前实现了BlockingMessageQueue,不开心可以使用Redis(暂未实现)
 */
public interface MessageQueue {

    public boolean pub(String queueName, Object obj, MessagePriority priority) throws IOException;

    public <T> T sub(String queueName, Class<T> tClass) throws IOException;

    public long size(String queueName) throws IOException;

    public void clear(String queueName) throws IOException;

    public void setQueueConfig(SpiderMessagePool messagePool);

    public int capacity();

    public void destory();

}
