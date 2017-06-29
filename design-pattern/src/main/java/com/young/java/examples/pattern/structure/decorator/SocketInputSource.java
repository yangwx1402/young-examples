package com.young.java.examples.pattern.structure.decorator;

/**
 * Created by yangyong3 on 2017/6/29.
 */
public class SocketInputSource implements Sourceable {

    private Sourceable sourceable;

    public SocketInputSource(Sourceable sourceable){
        this.sourceable = sourceable;
    }

    @Override
    public void source() {
        sourceable.source();
        System.out.println("socket input source");
    }
}
