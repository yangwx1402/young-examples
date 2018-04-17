package com.young.java.examples.singlelock.concrruent.lock;

import com.young.java.examples.singlelock.concrruent.lock.thread.TryLockMethodThread;

/**
 * @author shazam
 * @DATE 2018/4/14
 */
public class TryLockMethodExample extends LockExample {
    public static void main(String[] args) {
        LockExample example = new TryLockMethodExample();
        example.executes(10);
    }

    @Override
    public Thread newThread() {
        return new Thread(new TryLockMethodThread());
    }
}
