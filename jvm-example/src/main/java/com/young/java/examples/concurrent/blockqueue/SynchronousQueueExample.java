package com.young.java.examples.concurrent.blockqueue;

import java.util.concurrent.SynchronousQueue;

/**
 * Created by young.yang on 2016/11/21.
 * SynchronousQueue 类实现了 BlockingQueue 接口。
 SynchronousQueue 是一个特殊的队列，它的内部同时只能够容纳单个元素。
 如果该队列已有一元素的话，试图向队列中插入一个新元素的线程将会阻塞，
 直到另一个线程将该元素从队列中抽走。同样，如果该队列为空，试图向队列中抽取一个元素的线程将会阻塞，
 直到另一个线程向队列中插入了一条新的元素。
 据此，把这个类称作一个队列显然是夸大其词了。它更多像是一个汇合点。
 比如两个线程,一个put一个take,只有当第一个线程执行了put后就会等待,等待另一个线程take，如果另一个线程
 没有take那么第一个线程就会等待，反过来也一样，所以put和take必须是成对出现，同时退出。
 */
public class SynchronousQueueExample {

    public static class PutThread implements Runnable{

        private SynchronousQueue<Integer> synchronousQueue;

        public PutThread(SynchronousQueue<Integer> synchronousQueue){
               this.synchronousQueue = synchronousQueue;
        }

        @Override
        public void run() {
            while(true) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                try {

                    synchronousQueue.put(1);
                    System.out.println(Thread.currentThread().getName()+":put");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static class GetThread implements Runnable{

        private SynchronousQueue<Integer> synchronousQueue;

        public GetThread(SynchronousQueue<Integer> synchronousQueue){
            this.synchronousQueue = synchronousQueue;
        }

        @Override
        public void run() {
            while(true) {
                try {
                    System.out.println(Thread.currentThread().getName()+":"+synchronousQueue.take());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args){
        SynchronousQueue<Integer> queue = new SynchronousQueue();
        Thread thread1 = new Thread(new PutThread(queue));
        thread1.setName("thread1");
        Thread thread2 = new Thread(new GetThread(queue));
        thread2.setName("thread2");
        thread1.start();
        thread2.start();


    }

}
