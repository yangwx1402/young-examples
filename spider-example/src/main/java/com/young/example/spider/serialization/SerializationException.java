package com.young.example.spider.serialization;

/**
 * Created by young.yang on 2017/2/25.
 */
public class SerializationException extends Exception{

    public SerializationException(String message) {
        super(message);
    }

    public SerializationException(String message, Throwable cause) {
        super(message, cause);
    }
}
