package com.young.java.examples.singlelock.concrruent.readwrite;

import java.util.stream.IntStream;

import com.young.java.examples.singlelock.concrruent.readwrite.thread.ReadWriteThread;

/**
 * @author shazam
 * @DATE 2018/4/14
 */
public class ReadWriteLcokTestExample {
    public static void main(String[] args) {
        IntStream.range(0, 10).forEach(i -> {
            Thread thread = new Thread(new ReadWriteThread(i));
            thread.setName("thread_" + i);
            thread.start();
        });
    }
}
