package com.young.java.examples.pattern.structure.adapter.classadapter;

/**
 * Created by yangyong3 on 2017/6/29.
 * 类的适配器模式，也就是说将Source类适配到Target接口上去。
 */
public class ClassAdapter extends Source implements Target {
    @Override
    public void method2() {
       System.out.println("method2");
    }
}
