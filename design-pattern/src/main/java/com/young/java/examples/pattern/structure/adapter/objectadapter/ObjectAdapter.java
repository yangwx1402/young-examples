package com.young.java.examples.pattern.structure.adapter.objectadapter;

import com.young.java.examples.pattern.structure.adapter.classadapter.Source;
import com.young.java.examples.pattern.structure.adapter.classadapter.Target;

/**
 * Created by yangyong3 on 2017/6/29.
 * 对象的适配器模式，将对象适配到一个接口上去。
 */
public class ObjectAdapter implements Target {

    private Source source;

    public ObjectAdapter(Source source){
        this.source = source;
    }

    @Override
    public void method1() {
        source.method1();
    }

    @Override
    public void method2() {
        System.out.println("method2");
    }
}
