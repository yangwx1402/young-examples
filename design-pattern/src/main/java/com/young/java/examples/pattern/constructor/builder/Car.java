package com.young.java.examples.pattern.constructor.builder;

/**
 * Created by yangyong3 on 2017/6/29.
 * 建造器模式，如果一个类的构造函数太多，就可以使用建造器模式来简化构造器的编写
 */
public class Car {

    private String engine;

    private String tire;

    private String wheel;

    public String getEngine() {
        return engine;
    }

    public String getTire() {
        return tire;
    }

    public String getWheel() {
        return wheel;
    }

    private Car(CarBuilder builder){
        this.engine = builder.engine;
        this.tire = builder.tire;
        this.wheel = builder.wheel;
    }

    public static class CarBuilder {

        private String engine;

        private String tire;

        private String wheel;

        public CarBuilder(){
        }

        public CarBuilder setEngine(String engine) {
            this.engine = engine;
            return this;
        }

        public CarBuilder setTire(String tire) {
            this.tire = tire;
            return this;
        }

        public CarBuilder setWheel(String wheel) {
            this.wheel = wheel;
            return this;
        }

        public Car build() {
           return new Car(this);
        }
    }
}
