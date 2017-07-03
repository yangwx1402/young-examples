package com.young.java.examples.pattern.behaviour.chain;

/**
 * Created by yangyong3 on 2017/7/3.
 * 责任链模式，其实netty的handler模型，以及spring的filter模型都是采用责任链模式实现
 * 适用于链式调用的场景。
 */
public class ChainExample {
    public static void main(String[] args) {
        AbstractChain encode = new EncodeHandler();
        AbstractChain logic = new LogicHandler();
        encode.setHandler(logic);
        AbstractChain decode = new DecodeHandler();
        logic.setHandler(decode);
        encode.operate(new String("123"));
    }
}
