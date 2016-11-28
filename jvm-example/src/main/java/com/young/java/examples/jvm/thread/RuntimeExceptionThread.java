package com.young.java.examples.jvm.thread;

/**
 * Created by young.yang on 2016/11/14.
 */
public class RuntimeExceptionThread implements Runnable {
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getUncaughtExceptionHandler());
        throw new RuntimeException();
    }
}
