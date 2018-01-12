package com.young.java.examples.java18.lambda;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

/**
 * @author shazam
 * @DATE 2018/1/12
 */
public class Foreach {

    public static void main(String[] args){
       List<Integer> list = Arrays.asList(1,2,3,4,5,6);
       list.forEach(new Consumer<Integer>() {
           @Override
           public void accept(Integer integer) {
               System.out.println(integer);
           }
       });

       list.forEach(ele->System.out.println(ele));
    }
}
