package com.young.java.examples.quickstart;

import org.mockito.Mockito;

import java.util.List;

/**
 * Created by yangyong3 on 2017/7/5.
 */
public class HelloMockito {
    public static void mockList(){
        //mock一个List
        List<Integer> list = Mockito.mock(List.class);
        for(int i=0;i<10;i++){
            list.add(i);
        }
        //简单的验证
        Mockito.verify(list).add(1);
    }
    public static void main(String[] args){
        HelloMockito.mockList();
    }
}
