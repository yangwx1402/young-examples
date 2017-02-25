package com.young.example.spider.distribution.mq;

/**
 * Created by yangyong3 on 2017/2/21.
 */
public class MQException extends Exception {

    public MQException(String message) {
        super(message);
    }

    public MQException(String message, Throwable cause) {
        super(message, cause);
    }
}
