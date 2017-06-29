package com.young.java.examples.pattern.structure.decorator;

/**
 * Created by yangyong3 on 2017/6/29.
 */
public class FileInputSource implements Sourceable {
    @Override
    public void source() {
       System.out.println("file input source");
    }
}
