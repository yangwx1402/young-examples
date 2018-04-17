package com.young.java.examples.singlelock.concrruent.readwrite;

import com.young.java.examples.singlelock.concrruent.lock.LockExample;
import com.young.java.examples.singlelock.concrruent.readwrite.thread.ReadReadThread;

/**
 * @author shazam
 * @DATE 2018/4/14
 */
public class ReadReadExample extends LockExample{

    @Override
    public Thread newThread() {
        return new Thread(new ReadReadThread());
    }

    public static void main(String[] args){
        ReadReadExample example = new ReadReadExample();
        example.executes(10);
    }
}
