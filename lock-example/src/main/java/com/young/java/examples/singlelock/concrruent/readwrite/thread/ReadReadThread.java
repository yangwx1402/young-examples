package com.young.java.examples.singlelock.concrruent.readwrite.thread;

import java.util.Random;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author shazam
 * @DATE 2018/4/14
 */
public class ReadReadThread implements Runnable {

    private static final ReadWriteLock lock = new ReentrantReadWriteLock();

    private static final int interval = 10;

    private static final Random rand = new Random();

    @Override
    public void run() {
        try {
            while (true) {
                if (lock.readLock().tryLock()) {
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
            lock.readLock().unlock();
        }
    }
}
