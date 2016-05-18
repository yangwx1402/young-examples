package com.young.java.examples.spring.beanfactory;

import org.springframework.beans.factory.FactoryBean;

/**
 * Created by Administrator on 2016/5/18.
 * FactoryBean在Spring中用来生成比较复杂的Bean
 */
public class CarBeanFactory implements FactoryBean<Car> {

    private String carInfo;

    public String getCarInfo() {
        return carInfo;
    }

    public void setCarInfo(String carInfo) {
        this.carInfo = carInfo;
    }

    @Override
    public Car getObject() throws Exception {
        Car car = new Car();
        String[] split = carInfo.split("::");
        car.setInfo(split[0]);
        car.setName(split[1]);
        return car;
    }

    @Override
    public Class<?> getObjectType() {
        return Car.class;
    }

    @Override
    public boolean isSingleton() {
        return false;
    }
}
