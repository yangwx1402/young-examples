package com.young.java.examples.spring;

import com.young.java.examples.spring.beanfactory.Car;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by Administrator on 2016/5/22.
 */
public class ApplicationContextFactory {
    public static ApplicationContext getApplicationContenxt(String xmlPath) {
        ApplicationContext ac = new ClassPathXmlApplicationContext(xmlPath);
        return ac;
    }
}
