package com.young.java.examples.pattern.behaviour.listener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yangyong3 on 2017/6/30.
 */
public abstract class Subject {
    private final List<Listener> listeners = new ArrayList<>();

    public abstract void get();

    public abstract void update();

    public abstract void delete();

    public void addListener(Listener listener){
        listeners.add(listener);
    }

    public void notifyListeners(Event event){
        for(Listener listener:listeners){
            listener.action(event);
        }
    }
}
