package com.young.example.spider.distribution.mq;

import java.util.Collection;

/**
 * Created by yangyong3 on 2017/2/21.
 */
public class AbstractPriorityMessageQueue<T> extends AbstractMessageQueue<T> implements MessageQueue<T>{

    public AbstractPriorityMessageQueue(String queueName) {
        super(queueName);
    }

    protected void checkMessage(Object object) throws MQException{
        super.checkMessage(object);
        if(!(object instanceof Comparable))
            throw new MQException(object.toString()+" must implements Comparable interface");
    }

    @Override
    public void offer(T object) throws MQException {

    }

    @Override
    public void offer(Collection<T> objects) throws MQException {

    }

    @Override
    public T take(Class<T> tClass) throws MQException {
        return null;
    }

    @Override
    public Collection<T> takes(Class<T> tClass, int num) throws MQException {
        return null;
    }

    @Override
    public long size() throws MQException {
        return 0;
    }

    @Override
    public void clear() throws MQException {

    }

    @Override
    public void destory() throws MQException {

    }
}
