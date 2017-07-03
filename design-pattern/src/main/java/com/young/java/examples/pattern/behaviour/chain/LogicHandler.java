package com.young.java.examples.pattern.behaviour.chain;

/**
 * Created by yangyong3 on 2017/7/3.
 */
public class LogicHandler extends AbstractChain implements Handler {
    @Override
    public void operate(Object obj) {
        if(obj instanceof String) {
            System.out.println("这个是业务逻辑handler");
        }
        if(this.getHandler()!=null){
            this.getHandler().operate(obj);
        }
    }
}
