package com.young.java.examples.curator.path;

import com.young.java.examples.curator.exception.DistributedException;

/**
 * Created by young.yang on 2017/3/5.
 */
public class StorageException extends DistributedException {
    public StorageException(String message) {
        super(message);
    }

    public StorageException(String message, Throwable cause) {
        super(message, cause);
    }

    public StorageException(Throwable cause) {
        super(cause);
    }
}
