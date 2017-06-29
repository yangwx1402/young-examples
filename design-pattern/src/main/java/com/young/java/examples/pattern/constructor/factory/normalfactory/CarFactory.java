package com.young.java.examples.pattern.constructor.factory.normalfactory;

import com.young.java.examples.pattern.constructor.factory.AudiCar;
import com.young.java.examples.pattern.constructor.factory.BMWCar;
import com.young.java.examples.pattern.constructor.factory.BenzCar;
import com.young.java.examples.pattern.constructor.factory.Car;

/**
 * Created by yangyong3 on 2017/6/29.
 * 普通工厂模式,用在生产对象的场景中
 */
public class CarFactory {

    public Car getCar(Car.CarType type) throws Exception{
        if(Car.CarType.AUDI == type)
            return new AudiCar();
        if(Car.CarType.BENZ == type)
            return new BenzCar();
        if(Car.CarType.BMW == type)
            return new BMWCar();
        throw new Exception("car type is error");
    }

}
