package com.young.java.examples.jvm.instrumentation.dynamical;

import com.young.java.examples.jvm.instrumentation.statical.People;

/**
 * Created by yangyong3 on 2017/3/28.
 */
public class DynamicalExample {
    public static void main(String[] args){
        while(true){
            People people = new People();
            people.sayHello();
            try {
                Thread.sleep(1000*30);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
