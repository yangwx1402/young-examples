package com.young.java.examples.singlelock.concrruent.lock.thread;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author shazam
 * @DATE 2018/4/14
 */
public class TryLockTimeUnitMethodThread implements Runnable {

    private static final Lock lock = new ReentrantLock();

    private static final int interval = 10;

    private static final Random rand = new Random();

    @Override
    public void run() {

        try {
            while (true) {
                if (lock.tryLock(2, TimeUnit.SECONDS)) {
                    Thread.sleep(rand.nextInt(interval) * 1000);
                    System.out.println(Thread.currentThread().getName() + " run over");
                    return;
                } else {
                    System.out.println(Thread.currentThread().getName() + " wait ");
                }
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}
