package com.young.java.examples.proxy.cglib.real;

/**
 * Created by yangyong3 on 2017/3/30.
 */
public class DaoException extends Exception {
    public DaoException(String message) {
        super(message);
    }

    public DaoException(String message, Throwable cause) {
        super(message, cause);
    }

    public DaoException(Throwable cause) {
        super(cause);
    }
}
