package com.young.java.examples.pattern.structure.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by yangyong3 on 2017/6/29.
 */
public class DynamicProxy {

    public static class ProxyInvokeHandler implements InvocationHandler {

        private Subject real;

        public ProxyInvokeHandler(Subject real){
            this.real = real;
        }

        private void before() {
            System.out.println("before process");
        }

        private void after() {
            System.out.println("after process");
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            before();
            Object result = method.invoke(real, args);
            after();
            return result;
        }

    }

    public static void main(String[] args) {
        Subject proxy = (Subject) Proxy.newProxyInstance(DynamicProxy.class.getClassLoader(), new Class[]{Subject.class}, new ProxyInvokeHandler(new RealSubject()));
        proxy.process();
    }
}
