package com.young.java.examples.proxy.cglib.enhancer;

import com.young.java.examples.proxy.cglib.annotation.Transcationial;
import net.sf.cglib.proxy.CallbackFilter;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/**
 * Created by yangyong3 on 2017/3/30.
 * 回调过滤器
 */
public class UserDaoCallbackFilter implements CallbackFilter {
    @Override
    public int accept(Method method) {
        Annotation annotation = method.getAnnotation(Transcationial.class);
        if (annotation == null) {
            return 0;
        } else {
            return 1;
        }
    }
}
