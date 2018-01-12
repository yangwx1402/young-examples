package com.young.java.examples.java18.lambda;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Predicate;

/**
 * @author shazam
 * @DATE 2018/1/12
 */
public class RemoveIf {
    public static void main(String[] args) {
        ArrayList<Integer> list = new ArrayList<>();
        list.addAll(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8));
        list.removeIf(new Predicate<Integer>() {
            @Override
            public boolean test(Integer integer) {
                return integer < 4;
            }
        });
        System.out.println(list);

        list.removeIf(ele->ele<5);
        System.out.println(list);
    }
}
