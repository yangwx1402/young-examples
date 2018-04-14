package com.young.java.examples.singlelock.concrruent;

import com.young.java.examples.singlelock.concrruent.thread.LockMethodThread;

/**
 * @author shazam
 * @DATE 2018/4/14
 */
public class LockMethodExample extends LockExample {
    public static void main(String[] args) {
        LockExample example = new LockMethodExample();
        example.executes(10);
    }

    @Override
    public Thread newThread() {
        return new Thread(new LockMethodThread());
    }
}
