package com.young.java.examples.map;

import java.util.EnumMap;

/**
 * Created by yangyong3 on 2017/8/14.
 * 1、EnumMap中所有key都必须是单个枚举类的枚举值，创建EnumMap时必须显示或隐式指定它对应的枚举类。
 * 2、EnumMap根据key的自然顺序，即枚举值在枚举类中定义的顺序，来维护键值对的次序。
 * 3、EnumMap不允许使用null作为key值，但value可以。
 */
public class EnumMapExample {
    private static enum Status {
        READY, START, STARTED, RUNNING, STOPPING, STOPED, KILL, KILLED;
    }

    private EnumMap<Status, String> map = new EnumMap<Status, String>(Status.class);

    public void test() {
        map.put(Status.READY, Status.READY.name());
    }
}
