package com.young.example.spider.utils;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * Created by yangyong3 on 2017/2/23.
 */
public class ClassUtils {

    private static final Logger log = LogManager.getLogger(ClassUtils.class);

    public static <Obj> Obj newInstance(Class<Obj> objClass, Object[] args, Class... parameterTypes) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Constructor<Obj> constructor = objClass.getConstructor(parameterTypes);
        Obj obj = constructor.newInstance(args);
        log.info("reflect a object info is class=[" + objClass.getName() + "]");
        return obj;
    }

    public static <Obj> Obj newInstance(String classname, Object[] args, Class... parameterTypes) throws ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        Obj obj = (Obj) newInstance(Class.forName(classname), args, parameterTypes);
        log.info("reflect a object info is class=[" + classname + "]");
        return obj;
    }

    public static <Obj> Obj newInstance(String classname) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        Obj obj = (Obj) Class.forName(classname).newInstance();
        log.info("reflect a object info is class=[" + classname + "]");
        return obj;
    }
}
