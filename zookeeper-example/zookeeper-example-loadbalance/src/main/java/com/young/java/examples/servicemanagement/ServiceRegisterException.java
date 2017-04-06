package com.young.java.examples.servicemanagement;

/**
 * Created by yangyong3 on 2017/4/6.
 */
public class ServiceRegisterException extends Exception {
    public ServiceRegisterException() {
    }

    public ServiceRegisterException(String message) {
        super(message);
    }

    public ServiceRegisterException(String message, Throwable cause) {
        super(message, cause);
    }

    public ServiceRegisterException(Throwable cause) {
        super(cause);
    }
}
