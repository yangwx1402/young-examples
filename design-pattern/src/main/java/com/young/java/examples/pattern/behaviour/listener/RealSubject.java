package com.young.java.examples.pattern.behaviour.listener;

/**
 * Created by yangyong3 on 2017/6/30.
 */
public class RealSubject extends Subject {
    @Override
    public void get() {
        System.out.println("get");
        super.notifyListeners(new GetEvent());
    }

    @Override
    public void update() {
        System.out.println("update");
        super.notifyListeners(new UpdateEvent());
    }

    @Override
    public void delete() {
        System.out.println("delete");
        super.notifyListeners(new DeleteEvent());
    }
}
