package com.young.java.examples.java18.lambda;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author shazam
 * @DATE 2018/2/3
 */
public class Collector {
    public static void testCollector() {
        Stream<String> stream = Stream.of("i", "am", "super", "man");
        //List<String> list = stream.collect(Collectors.toList());
        //Set<String> set = stream.collect(Collectors.toSet());
        //System.out.println(list);
        //System.out.println(set);
        Map<String, Integer> map = stream.collect(Collectors.toMap(Function.identity(), String::length));
        System.out.println(map);

    }

    public static void testCollectorList(Stream<String> stream) {
        List<String> list = stream.collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
        System.out.println(list);
    }

    public static void testCollectorPart(Stream<String> stream){
        Map<Boolean,List<String>> collector = stream.collect(Collectors.partitioningBy(line->line.length()>2));
        System.out.println(collector);
    }

    public static void testCollectorGroupBy(Stream<String> stream){
        Map<Integer,List<String>> collector = stream.collect(Collectors.groupingBy(line->line.length()));
        System.out.println(collector);
    }

    public static void main(String[] args) {
        Stream<String> stream = Stream.of("i", "am", "super", "man");
        Collector.testCollector();
        Collector.testCollectorList(stream);
        Stream<String> stream1 = Stream.of("i", "am", "super", "man");
        Collector.testCollectorPart(stream1);
        Stream<String> stream2 = Stream.of("i", "am", "super", "man");
        Collector.testCollectorGroupBy(stream2);
    }
}
