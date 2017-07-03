package com.young.java.examples.mq;

/**
 * Created by yangyong3 on 2017/7/3.
 */
public class MessageQueueException extends Exception{

    public MessageQueueException(String message) {
        super(message);
    }

    public MessageQueueException(String message, Throwable cause) {
        super(message, cause);
    }

    public MessageQueueException(Throwable cause) {
        super(cause);
    }
}
