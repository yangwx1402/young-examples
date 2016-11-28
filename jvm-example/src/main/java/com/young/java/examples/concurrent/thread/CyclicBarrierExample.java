package com.young.java.examples.concurrent.thread;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * Created by young.yang on 2016/11/27.
 * 在所有参与者都已经在此 barrier 上调用 await方法之前，将一直等待。
 * 如果当前线程不是将到达的最后一个线程，出于调度目的，将禁用它，且在发生以下情况之一前，
 * 该线程将一直处于休眠状态：
 最后一个线程到达；或者
 其他某个线程中断当前线程；或者
 其他某个线程中断另一个等待线程；或者
 其他某个线程在等待 barrier 时超时；或者
 其他某个线程在此 barrier 上调用 reset()。
 如果当前线程：
 在进入此方法时已经设置了该线程的中断状态；或者
 在等待时被中断
 则抛出 InterruptedException，并且清除当前线程的已中断状态。如果在线程处于等待状态时
 barrier 被 reset()，或者在调用 await 时 barrier 被损坏，抑或任意一个线程正处于等待状态，
 则抛出 BrokenBarrierException 异常。
 如果任何线程在等待时被 中断，则其他所有等待线程都将抛出 BrokenBarrierException 异常，
 并将 barrier 置于损坏状态。
 如果当前线程是最后一个将要到达的线程，并且构造方法中提供了一个非空的屏障操作，
 则在允许其他线程继续运行之前，当前线程将运行该操作。如果在执行屏障操作过程中发生异常，
 则该异常将传播到当前线程中，并将 barrier 置于损坏状态。

 说白了就是一个Barrier有一个容量,只有当足够容量的Thraed调用了barrier的await方法以后，所有的
 线程才能继续往下运行,类似于多线程跑最后还有一个汇总线程,那么可以用barrier来实现,
 下面用赛跑的例子来讲解barrier类
 */
public class CyclicBarrierExample {

    private static final Random rand = new Random();

    private static class Runner implements Runnable{

        private CyclicBarrier barrier;

        private String name;

        public Runner(String name,CyclicBarrier barrier){
            this.barrier = barrier;
            this.name = name;
        }

        @Override
        public void run() {
            System.out.println(name+" 正在进行赛前准备");
            try {
                Thread.sleep(rand.nextInt(10)*1000);
                System.out.println(name +"已经准备好了，等待发令枪");
                barrier.await();
                System.out.println(name+" run");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }

        }
    }

    public static class RunResult implements Runnable{

        @Override
        public void run() {
            System.out.println("我是裁判,我來對你們賽跑進行計時");
        }
    }

    public static void main(String[] args){
        //這裡如果有4個的話,那麼將永遠等待了
        //CyclicBarrier barrier = new CyclicBarrier(3);
        CyclicBarrier barrier = new CyclicBarrier(3,new RunResult());
        String[] names = new String[]{"劉翔","羅伯遜","博爾特"};
        for(int i=0;i<3;i++){
            new Thread(new Runner(names[i],barrier)).start();
        }
    }
}
