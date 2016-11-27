package com.young.java.examples.concurrent.blockqueue;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * Created by young.yang on 2016/11/8.
 * DelayQueue的作用是用于放置实现了Delayed接口的对象，
 * 其中的对象只能在其到期时才能从队列中取走。这种队列是有序的，
 * 即队头对象的延迟到期时间最长。注意：不能将null元素放置到这种队列中。
 */
public class DelayQueueExample {

    private class DelayedElement implements Delayed{

        private long expireTime;

        public DelayedElement(int timeout){
            expireTime = System.currentTimeMillis()+timeout*1000;
        }

        @Override
        public long getDelay(TimeUnit unit) {
            //也就是說當該值返回0
           // System.out.println(createTime+":"+System.currentTimeMillis());
            return expireTime-System.currentTimeMillis();
        }

        @Override
        public int compareTo(Delayed o) {
            return 0;
        }
    }

    public void example() throws InterruptedException {
        DelayQueue<DelayedElement> queue = new DelayQueue<DelayedElement>();
        queue.put(new DelayedElement(4));
        System.out.println(queue.poll());
        System.out.println(queue.take());
    }
    public static void main(String[] args) throws InterruptedException {
        DelayQueueExample example = new DelayQueueExample();
        example.example();
    }
}
