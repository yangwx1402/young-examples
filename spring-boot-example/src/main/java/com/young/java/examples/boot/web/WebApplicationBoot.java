package com.young.java.examples.boot.web;

import org.springframework.boot.SpringApplication;

/**
 * Created by dell on 2016/8/18.
 */
public class WebApplicationBoot {
    public static void boot(Class[] resourses){
        SpringApplication.run(resourses,new String[]{});
    }

    public static void main(String[] args) {
        Class[] resources = new Class[]{FirstAction.class,HelloWorld.class};
        WebApplicationBoot.boot(resources);
    }
}
