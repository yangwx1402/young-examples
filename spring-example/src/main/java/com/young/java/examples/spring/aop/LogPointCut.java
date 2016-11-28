package com.young.java.examples.spring.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by young.yang on 2016/11/10.
 */
@Component
@Aspect
public class LogPointCut {
   @Pointcut("@annotation(com.young.java.examples.spring.aop.Log)")
    public void log(){}
    @Before("log()")
    public void before(JoinPoint joinPoint) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Class clazz = joinPoint.getTarget().getClass();
        System.out.println(clazz);
        String methodName = joinPoint.getSignature().getName();
        System.out.println(methodName);
        Object[] args = joinPoint.getArgs();
        Method method = null;
        Class[] argsClass = null;
        if(args!=null&&args.length>0) {
            argsClass = new Class[args.length];
            for (int i = 0; i < args.length; i++) {
                argsClass[i] = args[i].getClass();
            }
        }
        method = clazz.getMethod(methodName,argsClass);
        System.out.println(method);
        Annotation[] annotation = method.getAnnotations();
        System.out.println(annotation.length);
        Annotation annotation1 = method.getAnnotation(Log.class);
        System.out.println(annotation1);
        Method method1 = annotation1.getClass().getMethod("module");
        System.out.println(method1.invoke(annotation1));

    }
}
