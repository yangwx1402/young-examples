package com.young.java.examples.singlelock.concrruent.lock.thread;

import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author shazam
 * @DATE 2018/4/14
 */
public class LockMethodThread implements Runnable {

    private static final Lock lock = new ReentrantLock();

    private static final int interval = 10;

    private static final Random rand = new Random();

    @Override
    public void run() {
        try {
            /**
             * 这里所跟synchronized关键词作用是一样的,只不过我们可以控制锁的释放，而synchronized无法控制锁释放，释放动作是jvm层面做的
             */
            lock.lock();
            Thread.sleep(rand.nextInt(interval) * 1000);
            System.out.println(Thread.currentThread().getName() + " run over");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

    }
}
