package com.young.java.examples.pattern.behaviour.chain;

/**
 * Created by yangyong3 on 2017/7/3.
 */
public class DecodeHandler extends AbstractChain implements Handler {
    @Override
    public void operate(Object obj) {
        if (obj instanceof Byte[])
            System.out.println("想想netty的Decode Handler");
    }
}
