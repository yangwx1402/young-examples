package com.young.java.examples.curator.path;

import com.young.java.examples.curator.serilizable.Serializable;
import com.young.java.examples.curator.serilizable.SerializationException;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.imps.CuratorFrameworkState;

/**
 * Created by young.yang on 2017/3/5.
 */
public class ZkDistributedStorage<T> implements DistributedStorage<T> {

    private CuratorFramework framework;

    private Serializable<T> serializable;

    public ZkDistributedStorage(CuratorFramework framework,Serializable<T> serializable){
        this.framework = framework;
        this.serializable =serializable;
    }

    private void checkFramework() throws StorageException{
        if(CuratorFrameworkState.LATENT == framework.getState())
            framework.start();
        else if(CuratorFrameworkState.STOPPED == framework.getState()){
            throw new StorageException("CuratorFramework is closed ");
        }
    }

    private boolean exist(String key) throws StorageException{
        try {
            return framework.checkExists().forPath(key) != null;
        } catch (Exception e) {
            throw new StorageException(e);
        }
    }

    @Override
    public void set(String key, T t,boolean create) throws StorageException, SerializationException {
         if(exist(key)){
             try {
                 framework.setData().forPath(key,serializable.serializer(t));
             } catch (Exception e) {
                 throw new StorageException(e);
             }
         }else{

         }
    }

    @Override
    public void get(String key, Class<T> tClass) throws StorageException {

    }

    @Override
    public void del(String key) throws StorageException {

    }
}
