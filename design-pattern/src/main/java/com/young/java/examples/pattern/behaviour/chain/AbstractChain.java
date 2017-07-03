package com.young.java.examples.pattern.behaviour.chain;

/**
 * Created by yangyong3 on 2017/7/3.
 */
public abstract class AbstractChain implements Handler {

    private Handler handler;

    public Handler getHandler() {
        return handler;
    }

    public void setHandler(Handler handler) {
        this.handler = handler;
    }
}
