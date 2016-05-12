package com.young.java.examples.testcase.testframework.test;

import com.young.java.examples.testcase.testframework.annotations.TestCase;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2016/5/5.
 */
@TestCase("testExample")
public class TestExample {

    public void test1(){
        System.out.println("my name is yangyong"+",age is 31");
    }

    public Map<String,String> test2(){
        Map<String,String> map = new HashMap<String,String>();
        map.put("name","yangyong");
        map.put("age","30");
        return map;
    }
}
