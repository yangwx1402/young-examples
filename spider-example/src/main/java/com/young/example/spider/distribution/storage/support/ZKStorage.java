package com.young.example.spider.distribution.storage.support;

import com.young.example.spider.serialization.Serialization;
import com.young.example.spider.serialization.support.JsonSerialization;
import com.young.example.spider.distribution.storage.DistributeStorage;
import com.young.example.spider.distribution.storage.StorageException;
import com.young.example.spider.zkclient.ZKClientFactory;
import org.apache.curator.framework.CuratorFramework;

/**
 * Created by young.yang on 2017/2/25.
 */
public class ZKStorage<T> implements DistributeStorage<T> {

    private CuratorFramework zkClient;

    private static final String encode = "utf-8";

    private final Serialization<T,String> jsonSerialization = new JsonSerialization<T>();

    public ZKStorage(){
        this.zkClient = ZKClientFactory.getZKClient();
    }
    @Override
    public void store(String path, T t, boolean create) throws StorageException {
        try {
            if (zkClient.checkExists().forPath(path) == null) {
                if (create)
                    zkClient.create().forPath(path);
                else
                    throw new StorageException("path is not exist " + path);
            }
            zkClient.setData().forPath(path,jsonSerialization.serialization(t).getBytes(encode));
        }catch (Exception e){
            throw new StorageException("store "+t+" to path "+path+" error",e);
        }
    }

    @Override
    public T get(String path, Class<T> tClass) throws StorageException {
        try {
         if(zkClient.checkExists().forPath(path)==null)
             return null;
         byte[] bytes = zkClient.getData().forPath(path);
            if(bytes == null)
                return null;
            return jsonSerialization.unserialization(new String(bytes,encode),tClass);
        }catch (Exception e){
            throw new StorageException("get data from path "+path+" error",e);
        }
    }

    @Override
    public void del(String path) throws StorageException {
        try{
            if(zkClient.checkExists().forPath(path)==null)
                return;
            zkClient.delete().forPath(path);
        }catch (Exception e){
                throw new StorageException("path is not exist " + path);
        }
    }
}
