package com.young.java.examples.pattern.structure.decorator;

/**
 * Created by yangyong3 on 2017/6/29.
 * 装饰器模式，其实jdk里的IO流相关的类就是采用装饰器模式实现的
 * 作用就是将一个类装饰成为另一个类。
 */
public class DecoratorExample {
    public static void main(String[] args) {
        Sourceable fileInput = new FileInputSource();
        Sourceable socketInput = new SocketInputSource(fileInput);
        socketInput.source();
    }
}
