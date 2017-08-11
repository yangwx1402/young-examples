package com.young.java.examples.reference;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yangyong3 on 2017/8/11.
 */
public class StrongReferenceExample {

    //测试强引用
    public static void main(String[] args){
       List<String> list = new ArrayList<String>();
        //list这个引用对后面的ArrayList就是一个强引用，只有当list这个引用释放掉以后，
        //ArrayList对象才会回收，也就是说执行了obj=null才会回收,内存不够是会OOM的。
        for(int i=0;i<100000;i++){
            list.add("yangyong_"+i);
        }
    }
}
