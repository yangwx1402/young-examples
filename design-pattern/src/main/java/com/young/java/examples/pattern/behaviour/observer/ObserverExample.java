package com.young.java.examples.pattern.behaviour.observer;

/**
 * Created by yangyong3 on 2017/6/30.
 * 观察者模式，意思就是一个操作有多个观察者在观察，等操作发生变化的时候，需要通知到所有的观察者。
 * 后面还会讲监听器模式，跟观察者类似，不过监听器是通过事件传递来触发监听器的。
 */
public class ObserverExample {
    public static void main(String[] args) {
        Subject subject = new RealSubject();
        subject.addObserver(new Observer1());
        subject.addObserver(new Observer2());
        subject.operate();
    }
}
