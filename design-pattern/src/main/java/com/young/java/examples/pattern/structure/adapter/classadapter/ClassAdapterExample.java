package com.young.java.examples.pattern.structure.adapter.classadapter;

/**
 * Created by yangyong3 on 2017/6/29.
 */
public class ClassAdapterExample {
    public static void main(String[] args) {
        Target target = new ClassAdapter();
        target.method1();
        target.method2();
    }
}
