package com.young.java.examples.java18.lambda;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.UnaryOperator;

/**
 * @author shazam
 * @DATE 2018/1/12
 */
public class ReplaceAll {
    public static void main(String[] args) {
        ArrayList<Integer> list = new ArrayList<>();
        list.addAll(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9));
        list.replaceAll(new UnaryOperator<Integer>() {
            @Override
            public Integer apply(Integer integer) {
                return integer * 10;
            }
        });
        System.out.println(list);

        list.replaceAll(ele->ele+6);
        System.out.println(list);
    }
}
