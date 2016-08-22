package com.young.java.examples.jvm.oom;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by young.yang on 2016/8/20.
 * 测试方法区和运行时常量区OOM
 * 参数设置  -XX:PermSize=1M -XX:MaxPermSize=1M
 */
public class RuntimeConstantPoolOOM {

    /**
     * 会抛出异常 java.lang.OutofMemoryError:PermGen space 但是1.8Perm采用了外部物理内存,不会发生该异常
     * @param args
     */
    public static void main(String[] args){
        int index = 0;
        List<String> list = new ArrayList<String>();
        while(true){
            list.add(String.valueOf("yangyong"+(index++)).intern());
        }
    }
}
