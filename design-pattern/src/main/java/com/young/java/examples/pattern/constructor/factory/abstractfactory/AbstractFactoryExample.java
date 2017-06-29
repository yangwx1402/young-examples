package com.young.java.examples.pattern.constructor.factory.abstractfactory;

/**
 * Created by yangyong3 on 2017/6/29.
 */
public class AbstractFactoryExample {
    public static void main(String[] args) {
        CarFactory audiFactory = new AudiCarFactory();
        CarFactory benzFactory = new BenzCarFactory();
        System.out.println(audiFactory.getCar());
        System.out.println(benzFactory.getCar());
    }
}
