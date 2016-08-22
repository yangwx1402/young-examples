package com.young.java.examples.jvm.gc;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by young.yang on 2016/8/21.
 * 分析jdk1.8下GC日志,参数:-Xms30m -Xmx30m -Xmn8m -XX:+PrintGCDetails -verbose:gc -Xloggc:gc.log -XX:+DisableExplicitGC
 * 参数含义为:-Xms30m(初始化虚拟机内存) -Xmx30m(最大虚拟机内存) -Xmn8m(Young区内存) -XX:+PrintGCDetails(打印GC明细) -verbose:gc(打印gc日志) -Xloggc:gc.log(gc日志输出到文件) -XX:+DisableExplicitGC(禁用System.gc())
 */
public class Jvm18GCLogParse {

   private List<String> list = new ArrayList<String>();

    /**
     * 启动程序以后hotspot虚拟机参数如下
     * CommandLine flags: -XX:+DisableExplicitGC -XX:InitialHeapSize=31457280
     * -XX:MaxHeapSize=31457280 -XX:MaxNewSize=8388608 -XX:NewSize=8388608
     * -XX:+PrintGC -XX:+PrintGCDetails -XX:+PrintGCTimeStamps -XX:+UseCompressedClassPointers
     * -XX:+UseCompressedOops -XX:-UseLargePagesIndividualAllocation -XX:+UseParallelGC
     * 可以看到-XX:+UseParallelGC该参数,说明虚拟机默认的gc方式为young是Parallel Scavenge 而Old为 Seial Old
     * @param args
     * @throws InterruptedException
     * 内存分析
     * -Xms30m -Xmx30m -Xmn8m 发现更开始并没有FullGC 全都是GC,统计中GC(15次),FULLGC(50次)最后发现GC的情况下最慢的执行时间也在几毫秒,但是FullGc出现的情况下
     * 执行时间至少增加一个数据级,那么如何减少GC呢 很简单只要调大young区即可
     */
    /**
     * 最后抛出异常java.lang.OutOfMemoryError: GC overhead limit exceeded,大量频繁的FullGC会导致该异常
     * Sun 官方对此的定义是：“并行/并发回收器在GC回收时间过长时会抛出OutOfMemroyError。
     * 过长的定义是，超过98%的时间用来做GC并且回收了不到2%的堆内存。用来避免内存过小造成应用不能正常工作。“
     */
    public void processDefault(){
        long start = System.currentTimeMillis();
        List<String> temp = new ArrayList<String>();
         for(int i=0;i<10000;i++){
             list.add("yangyong_"+i);
             temp.add("yangyong_"+i);
         }
        System.out.println("cost time -"+(System.currentTimeMillis()-start));
    }
    public static void main(String[] args) throws InterruptedException {
       Jvm18GCLogParse parse = new Jvm18GCLogParse();
        while(true){
            parse.processDefault();
            Thread.sleep(20000);
        }
    }
}
