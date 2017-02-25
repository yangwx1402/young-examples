package com.young.example.spider.serialization.support;

import com.young.example.spider.serialization.Serialization;
import com.young.example.spider.serialization.SerializationException;
import com.young.example.spider.utils.JsonUtils;

import java.io.IOException;

/**
 * Created by young.yang on 2017/2/25.
 */
public class JsonSerialization<T> implements Serialization<T,String> {
    @Override
    public String serialization(T f) throws SerializationException {
        try {
            if(f instanceof String)
                return (String) f;
            return JsonUtils.toJson(f);
        } catch (IOException e) {
            throw new SerializationException("serialization "+f.getClass()+" "+f+" error",e);
        }
    }

    @Override
    public T unserialization(String t,Class<T> tClass) throws SerializationException {
        try {
            if(tClass.getSimpleName().equals("String"))
                return (T) t;
            return JsonUtils.fromJson(t,tClass);
        } catch (IOException e) {
            throw new SerializationException("unserialization "+tClass+" "+t+" error",e);
        }
    }
}
