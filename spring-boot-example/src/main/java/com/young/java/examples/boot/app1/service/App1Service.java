package com.young.java.examples.boot.app1.service;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * Created by dell on 2016/8/18.
 */
@Service
public class App1Service implements DisposableBean {

    @Value("${name}")
    private String name;

    @Override
    public void destroy() throws Exception {
        System.out.println("App1Service is destory");
    }

    public String getName(){
        return name;
    }
}
