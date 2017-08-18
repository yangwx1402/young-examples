package com.young.java.examples.guava.collections.imutable;

import com.google.common.collect.ImmutableSet;

/**
 * Created by yangyong3 on 2017/8/17
 * <p/>
 * 可变集合接口	属于JDK还是Guava	不可变版本
 * Collection	JDK	ImmutableCollection
 * List	JDK	ImmutableList
 * Set	JDK	ImmutableSet
 * SortedSet/NavigableSet	JDK	ImmutableSortedSet
 * Map	JDK	ImmutableMap
 * SortedMap	JDK	ImmutableSortedMap
 * Multiset	Guava	ImmutableMultiset
 * SortedMultiset	Guava	ImmutableSortedMultiset
 * Multimap	Guava	ImmutableMultimap
 * ListMultimap	Guava	ImmutableListMultimap
 * SetMultimap	Guava	ImmutableSetMultimap
 * BiMap	Guava	ImmutableBiMap
 * ClassToInstanceMap	Guava	ImmutableClassToInstanceMap
 * Table	Guava	ImmutableTable
 */
public class ImutableCollectionExample {
    public static void testImutableSet() {
        ImmutableSet<Integer> set = ImmutableSet.copyOf(new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9});
        set.parallelStream().forEach(System.out::println);
        set.stream().forEach(i->{
           System.out.println(i);
        });
    }
    public static void main(String[] args){
      ImutableCollectionExample.testImutableSet();
    }
}
