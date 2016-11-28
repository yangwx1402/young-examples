package com.young.java.examples.exception;

/**
 * Created by young.yang on 2016/11/12.
 */
public class LogStorageException extends Exception {
    public LogStorageException(String message){
        super(message);
    }

    public LogStorageException(String message,Throwable throwable){
        super(message,throwable);
    }
}
