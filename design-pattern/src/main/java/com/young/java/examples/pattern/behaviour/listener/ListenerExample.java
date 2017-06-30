package com.young.java.examples.pattern.behaviour.listener;

/**
 * Created by yangyong3 on 2017/6/30.
 * 监听器模式
 */
public class ListenerExample {
    public static void main(String[] args){
        Subject  subject = new RealSubject();
        subject.addListener(new ListenerImpl());
        subject.get();
        subject.delete();
    }
}
