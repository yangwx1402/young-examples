package com.young.java.examples.pattern.structure.adapter.interfaceadapter;

import com.young.java.examples.pattern.structure.adapter.classadapter.Target;

/**
 * Created by yangyong3 on 2017/6/29.
 */
public class InterfaceAdapterExample {
    public static void main(String[] args) {
        Target target = new Adapter1();
        target.method1();
    }
}
