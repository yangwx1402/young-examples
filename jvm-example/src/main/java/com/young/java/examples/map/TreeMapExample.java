package com.young.java.examples.map;

import java.util.Map;
import java.util.Random;
import java.util.TreeMap;

/**
 * Created by yangyong3 on 2017/8/14.
 */
public class TreeMapExample {

    //tree map是排序的
    private Map<Integer, Integer> tree = new TreeMap<>();

    public void sort() {
        TreeMap<Integer, Integer> temp = new TreeMap<>();
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            temp.put(random.nextInt(100), random.nextInt(1000));
        }
        for (Map.Entry<Integer, Integer> entry : temp.entrySet()) {
            System.out.println(entry.getKey() + ":" + entry.getValue());
        }
    }

    /**
     * 可以借助TreeMap的tail方法来实现一个一致性hash分区功能。
     */
    public void tail(){
        TreeMap<Integer, Integer> temp = new TreeMap<>();
        for(int i=0;i<100;i++){
            temp.put(i,i);
        }

        System.out.println(temp.tailMap(88));
        System.out.println(temp.tailMap(100));
        System.out.println(temp.tailMap(99).firstKey());
    }



    public static void main(String[] args) {
        TreeMapExample example = new TreeMapExample();
        example.sort();
        example.tail();
    }
}
