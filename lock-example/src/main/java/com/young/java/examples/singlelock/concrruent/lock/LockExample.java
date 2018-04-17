package com.young.java.examples.singlelock.concrruent.lock;

import java.util.concurrent.locks.Lock;
import java.util.stream.IntStream;

/**
 * @author shazam
 * @DATE 2018/4/14
 */
public abstract class LockExample {

    /**
     * @see Lock
     * Lock接口的6个方法,其中有4个方法用来获取锁，那么他们有啥不同
     *
     * void lock();  最常用的,某个线程获取了锁以后其他线程无法获取，直到拥有锁的线程释放
     * void lockInterruptibly() throws InterruptedException; 在等待获取锁的过程中可以通过调用interrupt打断等待
     * boolean tryLock();  尝试获取锁，有返回值，拿到了true，没拿到也不会等待会立即返回false。
     * boolean tryLock(long time, TimeUnit unit) throws InterruptedException;  同上 不过特殊地方是可以等待time,
     * 如果等待期间拿到了就执行，等待超时就返回false
     * void unlock();
     * Condition newCondition();
     *
     *
     * Lock和synchronized的选择
     *
     * 　　总结来说，Lock和synchronized有以下几点不同：
     *
     * 　　1）Lock是一个接口，而synchronized是Java中的关键字，synchronized是内置的语言实现；
     *
     * 　　2）synchronized在发生异常时，会自动释放线程占有的锁，因此不会导致死锁现象发生；而Lock在发生异常时，如果没有主动通过unLock()
     * 去释放锁，则很可能造成死锁现象，因此使用Lock时需要在finally块中释放锁；
     *
     * 　　3）Lock可以让等待锁的线程响应中断，而synchronized却不行，使用synchronized时，等待的线程会一直等待下去，不能够响应中断；
     *
     * 　　4）通过Lock可以知道有没有成功获取锁，而synchronized却无法办到。
     *
     * 　　5）Lock可以提高多个线程进行读操作的效率。
     *
     * 　　在性能上来说，如果竞争资源不激烈，两者的性能是差不多的，而当竞争资源非常激烈时（即有大量线程同时竞争），此时Lock的性能要远远优于synchronized。所以说，在具体使用时要根据适当情况选择。
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
