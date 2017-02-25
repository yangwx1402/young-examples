package com.young.example.spider.distribution.storage;

/**
 * Created by young.yang on 2017/2/25.
 */
public class StorageException extends Exception {

    public StorageException(String message) {
        super(message);
    }

    public StorageException(String message, Throwable cause) {
        super(message, cause);
    }
}
