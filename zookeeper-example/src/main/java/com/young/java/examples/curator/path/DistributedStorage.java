package com.young.java.examples.curator.path;

import com.young.java.examples.curator.serilizable.SerializationException;

/**
 * Created by young.yang on 2017/3/5.
 */
public interface DistributedStorage<T> {
    public void set(String key,T t,boolean create) throws StorageException, SerializationException;

    public void get(String key,Class<T> tClass) throws StorageException,SerializationException;

    public void del(String key) throws StorageException;
}
