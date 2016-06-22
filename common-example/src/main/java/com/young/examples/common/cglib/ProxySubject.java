package com.young.examples.common.cglib;

import net.sf.cglib.proxy.Callback;
import net.sf.cglib.proxy.Enhancer;

/**
 * Created by dell on 2016/5/30.
 */
public class ProxySubject {
    public Object getProxy(Class superClass, Callback callback) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(superClass);
        enhancer.setCallback(callback);
        return enhancer.create();
    }

    public static void main(String[] args) {
        ProxySubject proxySubject = new ProxySubject();
        RealSubject proxy = (RealSubject) proxySubject.getProxy(RealSubject.class, new LogInterceptor());
        proxy.run();
    }
}
