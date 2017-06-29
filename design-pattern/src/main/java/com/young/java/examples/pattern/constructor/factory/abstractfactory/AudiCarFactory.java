package com.young.java.examples.pattern.constructor.factory.abstractfactory;

import com.young.java.examples.pattern.constructor.factory.AudiCar;
import com.young.java.examples.pattern.constructor.factory.Car;

/**
 * Created by yangyong3 on 2017/6/29.
 */
public class AudiCarFactory extends CarFactory {
    @Override
    public Car getCar() {
        return new AudiCar();
    }
}
