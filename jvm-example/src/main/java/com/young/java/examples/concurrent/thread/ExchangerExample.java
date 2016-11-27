package com.young.java.examples.concurrent.thread;

import java.util.concurrent.Exchanger;

/**
 * Created by young.yang on 2016/11/27.
 * Exchanger可以用來進行線程之間的數據交換。
 */
public class ExchangerExample {

    public static class ExchangerThread implements Runnable{

        private String name;

        private String data;

        private Exchanger<String> exchanger;

        public ExchangerThread(String name,String data,Exchanger<String> exchanger){
            this.name = name;
            this.data = data;
            this.exchanger = exchanger;
        }

        @Override
        public void run() {
            //交换前的数据
            System.out.println(name+" has data "+data);
            try {
                this.data = this.exchanger.exchange(this.data);
                //交换以后的结果
                System.out.println(name+" has data "+data);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    public static void main(String[] args){
        Exchanger<String> exchanger = new Exchanger<>();
        Thread threadA = new Thread(new ExchangerThread("A","A",exchanger));
        Thread threadB = new Thread(new ExchangerThread("B","B",exchanger));
        threadA.start();
        threadB.start();
    }
}
