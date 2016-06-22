package com.young.examples.common.cglib;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.lang.reflect.Method;

/**
 * Created by dell on 2016/5/26.
 */
public class LogInterceptor implements MethodInterceptor {

    private static Log log = LogFactory.getLog(LogInterceptor.class);

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        long start = System.currentTimeMillis();
        Object obj = methodProxy.invokeSuper(o, objects);
        log.info("execute " + o.getClass().getName() + "." + method.getName() + " cost time -" + (System.currentTimeMillis() - start));
        return obj;
    }
}
