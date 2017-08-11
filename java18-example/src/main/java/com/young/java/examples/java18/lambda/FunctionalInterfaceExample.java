package com.young.java.examples.java18.lambda;

/**
 * Created by yangyong3 on 2017/7/26.
 * @FunctionalInterface是一个标记Annotation，主要用来标注该接口是一个函数式接口
 * 什么是函数式接口呢:就是只能有一个方法的接口,如果有多个方法的话，编译就会报错
 */
@FunctionalInterface
public interface FunctionalInterfaceExample {
    void method();
}
