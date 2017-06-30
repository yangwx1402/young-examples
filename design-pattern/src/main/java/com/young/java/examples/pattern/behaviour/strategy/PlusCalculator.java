package com.young.java.examples.pattern.behaviour.strategy;

/**
 * Created by yangyong3 on 2017/6/30.
 */
public class PlusCalculator extends AbstractCalculator implements ICalculator {
    @Override
    public int calculate(String exp) {
        int[] array = super.split(exp,"\\+");
        return array[0]+array[1];
    }
}
