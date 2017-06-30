package com.young.java.examples.pattern.structure.adapter.interfaceadapter;

import com.young.java.examples.pattern.structure.adapter.classadapter.Target;

/**
 * Created by yangyong3 on 2017/6/29.
 * 接口的适配器模式，有时候一个接口的方法有很多，但是并不是每个类都需要实现所有的方法，就可以采用接口的适配器模式，
 * 在Adapter中提供接口的默认实现，然后子类中只需要实现关心的方法即可。
 * 其实不管是什么适配器，都是将一个类、对象、接口适配到一个接口上去。
 */
public abstract class InterfaceAdapter implements Target {
    @Override
    public void method1() {

    }

    @Override
    public void method2() {

    }
}
