package com.young.java.examples.pattern.behaviour.listener;

/**
 * Created by yangyong3 on 2017/6/30.
 */
public class ListenerImpl implements Listener {
    @Override
    public void action(Event event) {
        if(event instanceof GetEvent){
            System.out.println("get event ");
        }else if(event instanceof UpdateEvent){
            System.out.println("update event");
        }else if(event instanceof DeleteEvent){
            System.out.println("delete event");
        }
    }
}
