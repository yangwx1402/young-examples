package com.young.java.examples.guava.range;

import com.google.common.collect.BoundType;
import com.google.common.collect.Range;

/**
 * guava的区间操作
 * @author shazam
 * @DATE 2018/4/17
 */
public class RangeExample {
    public static void main(String[] args){
        Range<Integer> range = Range.range(1, BoundType.OPEN,10,BoundType.CLOSED);
        System.out.println(range.upperEndpoint());
        System.out.println(range.lowerEndpoint());
        System.out.println(range.contains(5));
    }
}
