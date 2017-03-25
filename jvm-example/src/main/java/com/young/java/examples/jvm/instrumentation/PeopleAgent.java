package com.young.java.examples.jvm.instrumentation;

import java.lang.instrument.Instrumentation;

/**
 * Created by yangyong on 17-3-25.
 */
public class PeopleAgent {
    public static void premain(String agentArgs,Instrumentation inst){
        System.out.println("我是agent需要的参数列表,我的值为"+agentArgs);
        inst.addTransformer(new PeopleClassFileTrasformer());
    }
}
