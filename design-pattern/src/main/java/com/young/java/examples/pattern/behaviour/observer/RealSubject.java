package com.young.java.examples.pattern.behaviour.observer;

/**
 * Created by yangyong3 on 2017/6/30.
 */
public class RealSubject extends AbstractSubject {
    @Override
    public void operate() {
        System.out.println("操作 完成 发送给观察者");
        notifyObserver();
    }
}
