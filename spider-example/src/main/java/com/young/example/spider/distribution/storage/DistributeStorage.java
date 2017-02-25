package com.young.example.spider.distribution.storage;

/**
 * Created by young.yang on 2017/2/25.
 */
public interface DistributeStorage<T> {
    public void store(String path,T t,boolean create) throws StorageException;

    public T get(String path,Class<T> tClass) throws StorageException;

    public void del(String path) throws StorageException;
}
