package com.young.java.examples.spring.beanfactory;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by Administrator on 2016/5/18.
 */
public class CarBeanFactoryBoot {
    public static void main(String[] args){
        ApplicationContext ac = new ClassPathXmlApplicationContext("factory_bean.xml");
        //虽然xml中定义的是一个FactoryBean对象,但是在getBean的时候发现CarFactoryBean是一个FactoryBean
        //那么spring对调用其中的getObject方法获取Car对象返回,那么CarFactoryBean就是起到生成复杂对象的作用
        //如果想要获取CarFactoryBean对象可以使用ac.getBean("&car")
        Car car = (Car) ac.getBean("car");
        System.out.println(car.getName());
        System.out.println(ac.getBean("&car"));
    }
}
