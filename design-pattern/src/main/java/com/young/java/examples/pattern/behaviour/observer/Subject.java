package com.young.java.examples.pattern.behaviour.observer;

/**
 * Created by yangyong3 on 2017/6/30.
 * 事物接口
 */
public interface Subject {
    //实际的事物操作方法
    public void operate();

    public void addObserver(Observer observer);

    public void notifyObserver();


}
