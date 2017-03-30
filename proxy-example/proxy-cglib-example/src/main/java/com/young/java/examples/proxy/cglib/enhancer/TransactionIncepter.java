package com.young.java.examples.proxy.cglib.enhancer;

import com.young.java.examples.proxy.cglib.real.DaoException;
import com.young.java.examples.proxy.cglib.real.Transaction;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * Created by yangyong3 on 2017/3/30.
 */
public class TransactionIncepter implements MethodInterceptor {

    private Transaction transaction;

    public TransactionIncepter(Transaction transaction) {
        this.transaction = transaction;
    }

    /**
     *
     * @param o 代理类,增强了以后的类，有cglib生成
     * @param method   被代理类中的方法
     * @param objects  方法参数
     * @param methodProxy 增强类的方法
     * @return
     * @throws Throwable
     */
    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        Object result = null;
        if (transaction != null) {
            transaction.begin();
            try {
                System.out.println(o.getClass().getName());
                System.out.println(method.getName());
                System.out.println(methodProxy.getSuperName());
                //这里必须调用父类的方法,不然就无线循环拦截了
                result = methodProxy.invokeSuper(o, objects);
                transaction.commit();
                return result;
            } catch (DaoException e) {
                transaction.rollback();
                throw e;
            }
        }
        return methodProxy.invokeSuper(o, objects);
    }
}
