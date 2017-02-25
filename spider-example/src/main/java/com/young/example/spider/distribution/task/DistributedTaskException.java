package com.young.example.spider.distribution.task;

/**
 * Created by young.yang on 2017/2/25.
 */
public class DistributedTaskException extends Exception {
    public DistributedTaskException(String message) {
        super(message);
    }

    public DistributedTaskException(String message, Throwable cause) {
        super(message, cause);
    }
}
