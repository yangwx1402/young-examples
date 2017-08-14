package com.young.java.examples.jvm.thread;

/**
 * Created by yangyong3 on 2017/8/14.
 *volatile关键词主要是为了保证变量的可见性，jvm的内存模型为 read:内存--->jvm cache--->change--->write jvm cache--->内存
 * 因为加入了jvm的cache 所以多线程情况下，一个线程改变了变量值并刷新到内存以后，由于jvm cache的存在，可能另一个线程读取变量值
 * 的时候还是从jvm cache中读取的，就出现了变量修改后不可见的情况。
 * volatile关键词就是让jvm明白在读取数据和写入数据的时候不需要jvm cache的参与，都是直接读取和写入内存，这样就可以保证不同线程
 * 对同一变量的可见性。但是该关键词仍然不能解决原子性操作。也就是说该关键词解决的是数据的一致性，并不能解决原子性。在非原子性要求
 * 数据一致性的场景里可以使用该关键词起到提高程序性能的作用。
 * 而synchronized是通过加锁，他能保证变量的一致性和原子性。
 *
 */
public class VolitileExample {
    private volatile static boolean stop;

    private static int index;

    private  static class CountThread extends Thread {
        @Override
        public void run() {
            while (!stop) {
                System.out.println("我在做事情");
            }
            System.out.println("index="+index);
        }
    }

    public static void main(String[] args) {
        new CountThread().start();
        index = 42;
        stop = true;
    }
}
