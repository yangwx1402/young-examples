package com.young.java.examples.jvm.thread;

/**
 * Created by young.yang on 2016/11/14.
 */
public class ThreadExceptionHandler implements Thread.UncaughtExceptionHandler{
    @Override
    public void uncaughtException(Thread t, Throwable e) {
        System.out.println("sdfsfdsfdsd");
        System.out.println("caught "+e);
    }
}
