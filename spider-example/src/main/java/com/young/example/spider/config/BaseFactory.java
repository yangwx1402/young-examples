package com.young.example.spider.config;

import com.young.example.spider.utils.ClassUtils;

import java.util.Hashtable;
import java.util.Map;

/**
 * Created by young.yang on 2017/2/25.
 */
public abstract class BaseFactory {

    private static final Map<String,Object> instanceCache = new Hashtable<String,Object>();

    public static final <T>  T getInstance(String classname) throws IllegalAccessException, InstantiationException, ClassNotFoundException {
        if(instanceCache.containsKey(classname)){
            return (T) instanceCache.get(classname);
        }
        T t = ClassUtils.newInstance(classname);
        instanceCache.put(classname,t);
        return t;
    }
}
