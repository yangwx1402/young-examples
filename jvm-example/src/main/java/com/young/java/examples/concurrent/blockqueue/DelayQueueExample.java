package com.young.java.examples.concurrent.blockqueue;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * Created by young.yang on 2016/11/8.
 */
public class DelayQueueExample {

    private class DelayedElement implements Delayed{

        private long createTime;

        public DelayedElement(int timeout){
            createTime = System.currentTimeMillis()+timeout*1000;
        }

        @Override
        public long getDelay(TimeUnit unit) {
            //也就是說當該值返回0
           // System.out.println(createTime+":"+System.currentTimeMillis());
            return createTime-System.currentTimeMillis();
        }

        @Override
        public int compareTo(Delayed o) {
            return 0;
        }
    }

    public void example() throws InterruptedException {
        DelayQueue<DelayedElement> queue = new DelayQueue<DelayedElement>();
        queue.put(new DelayedElement(5));
        System.out.println(queue.take());
    }
    public static void main(String[] args) throws InterruptedException {
        DelayQueueExample example = new DelayQueueExample();
        example.example();
    }
}
