package com.young.java.examples.guava.collections.colllection;

import com.google.common.collect.HashMultiset;

import java.util.Random;

/**
 * Created by yangyong3 on 2017/8/17.
 * Multiset可以计数的Collection
 * Map	对应的Multiset	是否支持null元素
 * HashMap	HashMultiset	是
 * TreeMap	TreeMultiset	是（如果comparator支持的话）
 * LinkedHashMap	LinkedHashMultiset	是
 * ConcurrentHashMap	ConcurrentHashMultiset	否
 * ImmutableMap	ImmutableMultiset	否
 */
public class MultisetExample {

    private static final Random rand = new Random();

    public static void testHashMultiset() {

        HashMultiset<String> set = HashMultiset.create();
        for (int i = 0; i < 100; i++) {
            set.add(rand.nextInt(100) + "");
        }
        for (int i = 0; i < 100; i++) {
            System.out.println(i + " count =" + set.count(i + ""));
        }
        System.out.println(set.size());
    }

    public static void main(String[] args) {
        MultisetExample.testHashMultiset();
    }
}
