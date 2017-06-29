package com.young.java.examples.pattern.structure.proxy;

/**
 * Created by yangyong3 on 2017/6/29.
 */
public class ProxyExample {

    public static void main(String[] args){
       Subject proxy = new ProxySubject(new RealSubject());
        proxy.process();
    }
}
