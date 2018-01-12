package com.young.java.examples.java18.lambda;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Spliterator;

/**
 * @author shazam
 * @DATE 2018/1/12
 */
public class Spliterators {
    public static void main(String[] args){
        ArrayList<Integer> list = new ArrayList<>();
        list.addAll(Arrays.asList(1,2,3,4,5,6,7,8,9));
        Spliterator thisList = list.spliterator();

        Spliterator subList = thisList.trySplit();
        thisList.forEachRemaining(ele->System.out.println(ele));
        System.out.println("--------");
        subList.forEachRemaining(ele->System.out.println(ele));
    }
}
