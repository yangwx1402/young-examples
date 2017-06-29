package com.young.java.examples.pattern.structure.adapter.objectadapter;

import com.young.java.examples.pattern.structure.adapter.classadapter.Source;
import com.young.java.examples.pattern.structure.adapter.classadapter.Target;

/**
 * Created by yangyong3 on 2017/6/29.
 */
public class ObjectAdapterExample {
    public static void main(String[] args) {
        Target target = new ObjectAdapter(new Source());
        target.method1();
        target.method2();
    }
}
