package com.young.java.examples.pattern.constructor.factory.normalfactory;

import com.young.java.examples.pattern.constructor.factory.Car;

/**
 * Created by yangyong3 on 2017/6/29.
 */
public class FactoryExample {
    public static void main(String[] args) throws Exception {
        CarFactory factory = new CarFactory();
        System.out.println(factory.getCar(Car.CarType.AUDI));
    }
}
