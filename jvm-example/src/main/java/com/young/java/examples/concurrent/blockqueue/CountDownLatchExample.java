package com.young.java.examples.concurrent.blockqueue;

import java.util.concurrent.CountDownLatch;

/**
 * Created by young.yang on 2016/11/22.
 */
public class CountDownLatchExample {
    private static class Thread1 extends Thread{

        private CountDownLatch countDownLatch;

        public Thread1(CountDownLatch countDownLatch){
            this.countDownLatch = countDownLatch;
        }

        @Override
        public void run() {
            try {
                Thread.sleep(3000);
                System.out.println(Thread.currentThread().getName()+" execute over");
                countDownLatch.countDown();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private static class Thread2 extends Thread{

        private CountDownLatch countDownLatch;

        public Thread2(CountDownLatch countDownLatch){
            this.countDownLatch = countDownLatch;
        }

        @Override
        public void run() {
            try {
                Thread.sleep(5000);
                System.out.println(Thread.currentThread().getName()+" execute over");
                countDownLatch.countDown();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
       CountDownLatch countDownLatch = new CountDownLatch(2);
        Thread thread1 = new Thread1(countDownLatch);
        Thread thread2 = new Thread2(countDownLatch);
        thread1.start();
        thread2.start();
        countDownLatch.await();
        System.out.println("over");
    }
}
