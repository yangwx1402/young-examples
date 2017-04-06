package com.young.java.examples.curator.exception;

/**
 * Created by young.yang on 2017/3/5.
 */
public class DistributedException extends Exception {
    public DistributedException(String message) {
        super(message);
    }

    public DistributedException(String message, Throwable cause) {
        super(message, cause);
    }

    public DistributedException(Throwable cause) {
        super(cause);
    }
}
