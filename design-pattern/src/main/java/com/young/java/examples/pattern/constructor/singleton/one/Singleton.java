package com.young.java.examples.pattern.constructor.singleton.one;

/**
 * Created by yangyong3 on 2017/6/29.
 * 单例模式，第一种写法
 * 改写法在多线程并发环境下，可能会new多个instance实例，所以需要加锁
 */
public class Singleton {
    private static Singleton instance;

    private Singleton() {
    }

    /**
     * 加锁避免了多线程环境下多new对象的问题，但是影响了高并发环境下的性能问题，
     * 因为该方法加了锁就变成了但线程，所以这里的锁需要根据需求，加在最少的代码块上.
     * @return
     */
    public static synchronized Singleton getInstance() {
        if (instance == null) {
            instance = new Singleton();
        }
        return instance;
    }
    public static void main(String[] args){
      System.out.println(Singleton.getInstance());
    }
}
