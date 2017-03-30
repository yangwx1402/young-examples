package com.young.java.examples.proxy.cglib.enhancer;

import net.sf.cglib.proxy.CallbackFilter;

import java.lang.reflect.Method;

/**
 * Created by yangyong3 on 2017/3/30.
 */
public class UserDaoCallbackFilter implements CallbackFilter {
    @Override
    public int accept(Method method) {
        String name = method.getName();
        if("get".equals(name)){
            return 0;
        }else{
            return 1;
        }
    }
}
