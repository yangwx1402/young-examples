package com.young.example.spider.distribution.mq;

import java.util.Collection;

/**
 * Created by yangyong3 on 2017/2/16.
 * 消息队列接口
 */
public interface MessageQueue<T> {
    /**
     * 往消息队列里发送消息
     * @param object    消息对象
     * @throws MQException
     */
    void offer(T object) throws MQException;

    /**
     * 批量往消息队列里发送消息
     * @param objects   消息对象集合
     * @throws MQException
     */
    void offer(Collection<T> objects) throws MQException;

    /**
     * 从消息队列中获取消息
     * @param tClass    消息反序列化后的类对象
     * @return
     * @throws MQException
     */
     T take(Class<T> tClass) throws MQException;

    /**
     * 批量从消息队列中获取消息
     * @param tClass    消息反序列化后的类对象
     * @return
     * @throws MQException
     */
     Collection<T> takes(Class<T> tClass, int num) throws MQException;

    /**
     * 获取消息队列目前大小
     *
     * @return
     * @throws MQException
     */
    long size() throws MQException;

    /**
     * 清空消息队列
     * @throws MQException
     */
    void clear() throws MQException;

    /**
     * 销毁队列
     *
     * @throws MQException
     */
    void destory() throws MQException;

}
