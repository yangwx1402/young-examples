package com.young.java.examples.jvm.oom;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by young.yang on 2016/8/20.
 * 测试java虚拟机堆内存溢出
 * 设置了堆内存为20m,并且设置了OOM后dump下来内存快照便于分析
 * 测试参数为-Xms20m -Xmx20m -XX:+HeapDumpOnOutOfMemoryError
 */
public class HeapOOM {
    static class OOMObject{

    }

    /**
     * 堆内存溢出后异常信息为java.lang.OutOfMemoryError: Java heap space
     * @param args
     */
    public static void main(String[] args){
      List<OOMObject> list = new ArrayList<OOMObject>();
        while(true){
            list.add(new OOMObject());
        }
    }
}
