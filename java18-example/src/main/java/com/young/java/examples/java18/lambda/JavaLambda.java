package com.young.java.examples.java18.lambda;

import java.util.Arrays;

/**
 * Created by yangyong3 on 2017/7/26.
 */
public class JavaLambda {
    public static void testLambda(){
        final String split = ",";
        Arrays.asList(1,2,3,4,5,6,7,8,9).forEach(item->System.out.print(item+split));
    }
    public static void main(String[] args){
      JavaLambda.testLambda();
    }
}
