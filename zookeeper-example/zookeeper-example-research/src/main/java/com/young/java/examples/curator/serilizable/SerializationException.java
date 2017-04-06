package com.young.java.examples.curator.serilizable;

/**
 * Created by young.yang on 2017/3/5.
 */
public class SerializationException extends Exception {
    public SerializationException(String message) {
        super(message);
    }

    public SerializationException(String message, Throwable cause) {
        super(message, cause);
    }

    public SerializationException(Throwable cause) {
        super(cause);
    }
}
