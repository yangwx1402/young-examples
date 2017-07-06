package com.young.java.examples.serializable;

/**
 * Created by yangyong3 on 2017/7/6.
 */
public class DSerializationException extends Exception {

    public DSerializationException(String message) {
        super(message);
    }

    public DSerializationException(String message, Throwable cause) {
        super(message, cause);
    }

    public DSerializationException(Throwable cause) {
        super(cause);
    }
}
