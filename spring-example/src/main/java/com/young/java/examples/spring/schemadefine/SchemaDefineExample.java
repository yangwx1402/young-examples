package com.young.java.examples.spring.schemadefine;

import com.young.java.examples.spring.ApplicationContextFactory;
import org.springframework.context.ApplicationContext;

/**
 * Created by Administrator on 2016/5/22.
 */
public class SchemaDefineExample {
    public static void main(String[] args){
        ApplicationContext ac = ApplicationContextFactory.getApplicationContenxt("people-spring.xml");
        String[] names = ac.getBeanDefinitionNames();
        for(String name:names){
            System.out.println(name);
        }
//        People people =  ac.getBean(People.class);
//
//        System.out.println(people.getName());
    }
}
