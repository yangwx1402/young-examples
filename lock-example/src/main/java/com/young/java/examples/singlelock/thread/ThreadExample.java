package com.young.java.examples.singlelock.thread;

import java.util.Random;

/**
 * @author shazam
 * @DATE 2018/4/14
 */
public class ThreadExample implements Runnable {

    private static final int interval = 10;

    private static final Random rand = new Random();

    private static final Object atmoicLock = new Object();

    @Override
    public void run() {
        try {
            /**
             * 原子锁，放在这里那么synchronized关键字内部的代码就变成了单线程
             * 如果多个线程都只是进行读操作，所以当一个线程在进行读操作时，其他线程只能等待无法进行读操作
             */
            synchronized (atmoicLock) {
                Thread.sleep(rand.nextInt(10 * 1000));
                System.out.println(Thread.currentThread().getName() + " run over");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
