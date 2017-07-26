package com.young.java.examples.java18.defaultmethod;

/**
 * Created by yangyong3 on 2017/7/26.
 */
public class DefaultMethodExample {
    public static void main(String[] args) {
        Java8DefaultInterface impl1 = InterfaceStaticMethod.create(Java8DefaultInterfaceImpl::new);
        Java8DefaultInterface impl2 = InterfaceStaticMethod.create(Java8DefaultOverride::new);
        impl1.method();
        System.out.println(impl1);
        impl2.method();
        System.out.println(impl2);
    }
}
