package com.young.java.examples.pattern.constructor.singleton.three;

/**
 * Created by yangyong3 on 2017/6/29.
 * 单例模式，第二种写法，这种写法避免了加锁，利用了类加载器(jvm)只加载一次，但是如果构造函数中有异常的话，就没办法处理异常信息
 */
public class Singleton {
    private static final Singleton instance = new Singleton();

    private Singleton(){}

    public static Singleton getInstance(){
        return instance;
    }
    public static void main(String[] args){
      System.out.println(Singleton.getInstance());
    }
}
