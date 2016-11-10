package com.young.java.examples.spring.aop;

import org.springframework.stereotype.Component;

/**
 * Created by young.yang on 2016/11/10.
 */
@Component
public class Bussiness {
    @Log(module = "test")
    public void run(){
        System.out.println("Bussiness run()");
    }
}
