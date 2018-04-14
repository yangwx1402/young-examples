package com.young.java.examples.singlelock.concrruent;

import java.util.stream.IntStream;

/**
 * @author shazam
 * @DATE 2018/4/14
 */
public abstract class LockExample {

    /**
     * Lock接口的6个方法,其中有4个方法用来获取锁，那么他们有啥不同
     *
     * void lock();  最常用的,某个线程获取了锁以后其他线程无法获取，直到拥有锁的线程释放
     * void lockInterruptibly() throws InterruptedException;
     * boolean tryLock();  尝试获取锁，有返回值，拿到了true，没拿到也不会等待会立即返回false。
     * boolean tryLock(long time, TimeUnit unit) throws InterruptedException;  同上 不过特殊地方是可以等待time,
     * 如果等待期间拿到了就执行，等待超时就返回false
     * void unlock();
     * Condition newCondition();
     */

    public void executes(int threads) {
        IntStream.range(0, threads).forEach(i -> {
            Thread thread = newThread();
            thread.setName("thread_" + i);
            thread.start();
        });
    }

    public abstract Thread newThread();
}
