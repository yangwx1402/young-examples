package com.young.java.examples.curator.serilizable;

/**
 * Created by young.yang on 2017/3/5.
 */
public interface Serializable<T> {

    public byte[] serializer(T t) throws SerializationException;

    public T deSerializer(byte[] bytes) throws SerializationException;
}
