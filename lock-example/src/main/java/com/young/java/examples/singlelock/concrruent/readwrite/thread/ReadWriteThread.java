package com.young.java.examples.singlelock.concrruent.readwrite.thread;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author shazam
 * @DATE 2018/4/14
 * WriteWrite的情况同Write和Read
 */
public class ReadWriteThread implements Runnable {

    private static final ReadWriteLock lock = new ReentrantReadWriteLock();

    private static final int interval = 10;

    private static final Random rand = new Random();

    private int threadIndex;

    public ReadWriteThread(int threadIndex) {
        this.threadIndex = threadIndex;
    }

    @Override
    public void run() {
        Lock nowLock = threadIndex % 2 == 0 ? lock.readLock() : lock.writeLock();
        try {
            while (true) {
                if (nowLock.tryLock(2, TimeUnit.SECONDS)) {
                    Thread.sleep(rand.nextInt(interval) * 1000);
                    System.out.println(Thread.currentThread().getName() + " read over");
                    return;
                } else {
                    System.out.println(Thread.currentThread().getName() + " wait read");
                }
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            nowLock.unlock();
        }
    }
}
