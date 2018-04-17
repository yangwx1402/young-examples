package com.young.java.examples.singlelock.concrruent.lock.thread;

import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author shazam
 * @DATE 2018/4/14
 */
public class TryLockMethodThread implements Runnable {

    private static final Lock lock = new ReentrantLock();

    private static final Random rand = new Random();

    private static final int interval = 10;

    @Override
    public void run() {
        try {
            while (true) {
                if(lock.tryLock()) {
                    Thread.sleep(rand.nextInt(interval) * 1000);
                    System.out.println(Thread.currentThread().getName() + " run over");
                    return;
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}
