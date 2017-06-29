package com.young.java.examples.pattern.structure.proxy;

/**
 * Created by yangyong3 on 2017/6/29.
 */
public class RealSubject implements Subject {
    @Override
    public void process() {
        System.out.println("real method process");
    }
}
