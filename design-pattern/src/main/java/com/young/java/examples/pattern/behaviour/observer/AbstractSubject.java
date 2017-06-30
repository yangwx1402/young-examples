package com.young.java.examples.pattern.behaviour.observer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yangyong3 on 2017/6/30.
 */
public abstract class AbstractSubject implements Subject {

    private final List<Observer> observers = new ArrayList<Observer>();

    @Override
    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void notifyObserver() {
       if(!observers.isEmpty()){
           for(Observer observer:observers){
               observer.update();
           }
       }
    }
}
