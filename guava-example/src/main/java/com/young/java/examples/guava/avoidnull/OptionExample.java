package com.young.java.examples.guava.avoidnull;

import com.google.common.base.Optional;


/**
 * Created by yangyong3 on 2017/8/16.
 * Option主要用于避免null的情况，跟scala里的Option一样
 */
public class OptionExample {
    public static void main(String[] args){
        Optional<Integer> number = Optional.of(5);
        System.out.println(number.get());
        System.out.println(number.or(10));
        //检查number中是否包含null，不包含返回true
        System.out.println(number.isPresent());

        Optional<Integer> number2 = Optional.fromNullable(null);
        System.out.println(number2.or(10));

    }
}
