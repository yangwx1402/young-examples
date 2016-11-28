package com.young.java.examples.jvm.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by young.yang on 2016/11/14.
 */
public class ThreadExceptionExample {
    public static void main(String[] args){
        //可以捕获RuntimeException
        ExecutorService execitors = Executors.newCachedThreadPool(new ExectorThreadFactory());
        execitors.execute(new Thread(new RuntimeExceptionThread()));
        execitors.shutdown();
        //不能捕获
        Thread t1 = new Thread(new RuntimeExceptionThread());
        t1.start();
    }

}
