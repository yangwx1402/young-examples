package com.young.java.examples.jvm.instrumentation.statical;

import java.lang.instrument.Instrumentation;

/**
 * Created by yangyong on 17-3-25.
 */
public class PeopleAgent {
    public static void premain(String agentArgs,Instrumentation inst){
        System.out.println("我是agent需要的参数列表,我的值为"+agentArgs);
        inst.addTransformer(new PeopleClassFileTrasformer());
    }

    public static void agentmain(String agentArgs,Instrumentation inst){
        Class[] classes = inst.getAllLoadedClasses();
        for(Class clazz:classes){
            System.out.println(clazz.getName());
        }
        inst.addTransformer(new PeopleClassFileTrasformer());
    }
}
