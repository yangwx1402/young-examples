package com.young.java.examples.pattern.constructor.builder;

/**
 * Created by yangyong3 on 2017/6/29.
 */
public class BuilderExample {
    public static void main(String[] args) {
        Car.CarBuilder builder = new Car.CarBuilder();
        builder.setEngine("engine").setTire("tire").setWheel("wheel");
        Car car = builder.build();
        System.out.println(car.getEngine());
    }
}
