package com.young.java.examples.java18.lambda;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

/**
 * @author shazam
 * @DATE 2018/1/12
 */
public class Sort {
    public static void main(String[] args) {
        ArrayList<Integer> list = new ArrayList<>();
        list.addAll(Arrays.asList(1, 9, 8, 4, 6, 5, 7, 3, 2));
        list.sort(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1.compareTo(o2);
            }
        });
        System.out.println(list);
        list.sort((e1, e2) -> e2.compareTo(e1));
        System.out.println(list);
    }
}
