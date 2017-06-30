package com.young.java.examples.pattern.behaviour.strategy;

/**
 * Created by yangyong3 on 2017/6/30.
 */
public class AbstractCalculator {

    public int[] split(String exp,String split){
        String[] temp = exp.split(split);
        int[] result = new int[2];
        result[0] = Integer.parseInt(temp[0].trim());
        result[1] = Integer.parseInt(temp[1].trim());
        return result;
    }
}
