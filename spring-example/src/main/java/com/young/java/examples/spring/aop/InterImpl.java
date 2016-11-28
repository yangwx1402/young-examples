package com.young.java.examples.spring.aop;

import org.springframework.stereotype.Component;

/**
 * Created by young.yang on 2016/11/10.
 */
@Component
public class InterImpl implements Inter {
    @Log(module = "inter")
    @Override
    public void run() {
        System.out.println("InterImpl run()");
    }
}
