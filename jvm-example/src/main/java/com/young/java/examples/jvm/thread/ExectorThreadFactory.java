package com.young.java.examples.jvm.thread;

import java.util.concurrent.ThreadFactory;

/**
 * Created by young.yang on 2016/11/14.
 */
public class ExectorThreadFactory implements ThreadFactory {
    @Override
    public Thread newThread(Runnable r) {
        Thread thread = new Thread(r);
        thread.setUncaughtExceptionHandler(new ThreadExceptionHandler());
        return thread;
    }
}
