package com.young.java.examples.java18.defaultmethod;

import java.util.function.Supplier;

/**
 *
 * @author yangyong3
 * @date 2017/7/26
 */
public interface InterfaceStaticMethod {
    static Java8DefaultInterface create(Supplier<Java8DefaultInterface> supplier){
        return supplier.get();
    }
}
