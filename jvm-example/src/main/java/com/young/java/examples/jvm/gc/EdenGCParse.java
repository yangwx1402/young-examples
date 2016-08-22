package com.young.java.examples.jvm.gc;

/**
 * Created by young.yang on 2016/8/21.
 * 分析Young去内存回收机制
 *
 */
public class EdenGCParse {
    private static final int _1MB = 1024*1024;

    /**
     * 虚拟机参数-Xms20M -Xmx20M -Xmn10M -verbose:gc -XX:+PrintGCDetails -XX:SurvivorRatio=8
     * 堆共20M内存,Eden与Survivor的比例是8:1 也就是说Eden有8M内存 而生存区和交换区共2M  而老年区是10M
     */

    public static void edenAllocation(){
        byte[] obj1,obj2,obj3,obj4;
        //分配了一个2M的内存对象
        obj1 = new byte[2*_1MB];
        obj2 = new byte[2*_1MB];
        obj3 = new byte[2*_1MB];
        //已经6M了还剩下2M,我们在分配一个4M的空间肯定是不够了这里肯定会进行GC
        obj4 = new byte[4*_1MB];
    }
    public static void main(String[] args){
        EdenGCParse.edenAllocation();
    }
}
