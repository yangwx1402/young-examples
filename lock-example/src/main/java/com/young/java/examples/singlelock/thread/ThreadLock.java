package com.young.java.examples.singlelock.thread;

import java.util.stream.IntStream;

/**
 * 线程锁
 *
 * @author shazam
 * @DATE 2018/4/14
 */
public class ThreadLock {

    public static void main(String[] args) {
        IntStream.range(0, 10).parallel().forEach(i -> {
            Thread thread = new Thread(new ThreadExample());
            thread.setName("thread_" + i);
            thread.start();
        });
    }
}
