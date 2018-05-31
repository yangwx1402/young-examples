package com.young.java.examples.java18.lambda;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author shazam
 * @DATE 2018/4/10
 * 接下来，当把一个数据结构包装成 Stream 后，就要开始对里面的元素进行各类操作了。常见的操作可以归类如下。
 *
 * Intermediate：
 * map (mapToInt, flatMap 等)、 filter、 distinct、 sorted、 peek、 limit、 skip、 parallel、 sequential、 unordered
 *
 * Terminal：
 * forEach、 forEachOrdered、 toArray、 reduce、 collect、 min、 max、 count、 anyMatch、 allMatch、 noneMatch、 findFirst、
 * findAny、 iterator
 *
 * Short-circuiting：
 * anyMatch、 allMatch、 noneMatch、 findFirst、 findAny、 limit
 */
public class StreamOperations {

    public static void map() {
        Stream.of(1, 2, 3, 4, 5).map(ele -> ele * 2).parallel().filter(ele -> ele > 4).sequential().sorted(
            (first, second) -> -first.compareTo(second)).forEach(
            System.out::println);
    }

    public static void flatMap() {
        Stream.of(Arrays.asList(1, 2, 3), Arrays.asList(4, 5, 6), Arrays.asList(7, 8, 9)).flatMap(
            list -> list.stream()).forEach(System.out::println);
    }

    public static void peek() {
        System.out.println("---------");
        Stream.of(1, 2, 3, 4, 5).peek(System.out::println);
        Stream.of(1, 2, 3, 4, 5).peek(System.out::println).collect(Collectors.toCollection(ArrayList::new)).forEach(
            System.out::println);
    }

    public static void findFirst() {
        System.out.println("---------");
        Stream.of(1, 2, 3, 4, 5).findFirst().ifPresent(System.out::println);
    }

    public static void reduce() {
        System.out.println("-----------");
        Stream.of(1, 2, 3, 4, 5).parallel().reduce((first, second) -> first + second).ifPresent(System.out::println);
    }

    public static void main(String[] args) {
        StreamOperations.map();
        StreamOperations.flatMap();
        StreamOperations.peek();
        StreamOperations.findFirst();
        StreamOperations.reduce();
    }
}
