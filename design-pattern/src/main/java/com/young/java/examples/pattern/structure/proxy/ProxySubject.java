package com.young.java.examples.pattern.structure.proxy;

/**
 * Created by yangyong3 on 2017/6/29.
 */
public class ProxySubject implements Subject {

    private Subject real;

    public ProxySubject(RealSubject realSubject) {
        this.real = realSubject;
    }

    private void before(){
        System.out.println("before process");
    }

    @Override
    public void process() {
        before();
        real.process();
        after();
    }

    private void after(){
        System.out.println("after process");
    }
}
