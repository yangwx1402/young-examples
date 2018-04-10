package com.young.java.examples.java18.lambda;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @author shazam
 * @DATE 2018/4/10
 */
public class StreamExample {
    /**
     * arrty to stream
     */
    public static void array2Stream() {
        Integer[] array = new Integer[10];
        for (int i = 0; i < 10; i++) {
            array[i] = i;
        }
        Stream stream = Stream.of(array);
        stream.forEach(System.out::println);
        Stream stream1 = Stream.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 0);
        stream1.forEach(System.out::println);
        Stream stream2 = Arrays.stream(array);
        stream2.forEach(System.out::println);
    }

    /**
     * StreamExample
     * 基本类型的stream,省去了boxing过程
     */
    public static void noBox2Stream() {
        IntStream intStream = IntStream.of(1, 2, 3, 4, 5, 6, 7, 8, 9);
        DoubleStream doubleStream = DoubleStream.of(1.0, 2.0, 3.0, 4.0, 5.0, 6.0, 7.0);
        IntStream.range(1, 10).forEach(System.out::println);
        double value = doubleStream.parallel().filter(ele -> ele > 3.0).map(ele -> ele * 2).reduce(
            (first, second) -> first + second).getAsDouble();
        System.out.println(value);
    }

    /**
     * stream to array
     */
    public static void stream2Array() {
        IntStream stream = IntStream.of(1, 2, 3, 4, 5, 6, 7);
        int[] array = stream.toArray();
        for (int i = 0; i < array.length; i++) {
            System.out.println(i);
        }
    }

    /**
     * stream to some of collection
     */
    public static void stream2Collection() {
        List<Integer> list = Stream.of(1, 2, 3, 4, 5, 6, 6).collect(Collectors.toList());
        Set<Integer> set = Stream.of(1, 2, 3, 4, 5).collect(Collectors.toCollection(HashSet::new));
        Map<String, String> map = Stream.of(1, 2, 3, 4, 5, 6, 7, 8).collect(
            Collectors.toMap(ele -> ele.toString(), ele -> ele.toString()));
        Stack<Integer> stack = Stream.of(1, 2, 3, 4, 5).collect(Collectors.toCollection(Stack::new));
        Queue<Integer> queue = Stream.of(1, 2, 3, 4, 5, 6).collect(Collectors.toCollection(LinkedBlockingQueue::new));
        List<Integer> collector = Stream.of(1, 2, 3, 4, 5, 6, 7, 8, 9).collect(ArrayList::new, List::add, List::addAll);

    }

    public static void ioStream2Stream() throws FileNotFoundException {
        Stream<String> lines = new BufferedReader(
            new InputStreamReader(StreamExample.class.getResourceAsStream("/data.txt"))).lines();
        lines.forEach(System.out::println);
    }

    public static void main(String[] args) throws FileNotFoundException {
        StreamExample.array2Stream();
        StreamExample.noBox2Stream();
        StreamExample.stream2Array();
        StreamExample.stream2Collection();
        StreamExample.ioStream2Stream();
    }
}
