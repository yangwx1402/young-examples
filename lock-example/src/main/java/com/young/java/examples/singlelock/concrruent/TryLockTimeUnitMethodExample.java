package com.young.java.examples.singlelock.concrruent;

import com.young.java.examples.singlelock.concrruent.thread.TryLockTimeUnitMethodThread;

/**
 * @author shazam
 * @DATE 2018/4/14
 */
public class TryLockTimeUnitMethodExample extends LockExample{
    @Override
    public Thread newThread() {
        return new Thread(new TryLockTimeUnitMethodThread());
    }
    public static void main(String[] args){
        LockExample example = new TryLockTimeUnitMethodExample();
        example.executes(10);
    }
}
