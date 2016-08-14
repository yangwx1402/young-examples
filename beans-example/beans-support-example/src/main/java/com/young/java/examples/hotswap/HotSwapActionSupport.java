package com.young.java.examples.hotswap;

import com.young.java.examples.classloader.hotswap.HotSwapAction;

/**
 * Created by Administrator on 2016/8/14.
 */
public class HotSwapActionSupport implements HotSwapAction {
    @Override
    public void sayHello() {
        System.out.println("hello world (version 6)");
    }
}
