package com.young.java.examples.stub;

import org.mockito.Mockito;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by yangyong3 on 2017/7/5.
 * Mockito打桩测试
 */
public class StubExample {
    public static void stub(){
        List list = Mockito.mock(LinkedList.class);
        Mockito.when(list.get(0)).thenReturn(1);
        Mockito.when(list.get(10)).thenThrow(new RuntimeException("没有这么多"));
        //输出打桩的值1
        System.out.println(list.get(0));
        //没有打桩，输出null
        System.out.println("9="+list.get(9));
        Mockito.verify(list).get(0);
        //抛出RuntimeException
        list.get(10);

    }
    public static void main(String[] args){
      StubExample.stub();
    }
}
