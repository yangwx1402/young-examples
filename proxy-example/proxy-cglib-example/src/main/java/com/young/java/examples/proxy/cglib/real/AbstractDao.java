package com.young.java.examples.proxy.cglib.real;

/**
 * Created by yangyong3 on 2017/3/30.
 */
public abstract class AbstractDao implements Dao{

    private ThreadLocal<DaoSession> daoSeesion = new ThreadLocal<>();

    public void setDaoSession(DaoSession daoSession1) {
        daoSeesion.set(daoSession1);
    }

    public DaoSession getDaoSession(){
        return daoSeesion.get();
    }
}
