package com.young.java.examples.pattern.constructor.factory.abstractfactory;

import com.young.java.examples.pattern.constructor.factory.BenzCar;
import com.young.java.examples.pattern.constructor.factory.Car;

/**
 * Created by yangyong3 on 2017/6/29.
 */
public class BenzCarFactory extends CarFactory{
    @Override
    public Car getCar() {
        return new BenzCar();
    }
}
