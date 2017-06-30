package com.young.java.examples.pattern.behaviour.strategy;

/**
 * Created by yangyong3 on 2017/6/30.
 * 策略模式，把不同的操作抽象出统一的接口，可以按照不同的策略去实现。
 */
public class StrategyExample {
    public static void main(String[] args) {
        ICalculator calculator = new PlusCalculator();
        System.out.println(calculator.calculate("1+1"));
        ICalculator calculator1 = new MinusCalculator();
        System.out.println(calculator1.calculate("1-1"));
    }
}
