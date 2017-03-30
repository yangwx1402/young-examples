package com.young.java.examples.proxy.cglib.real;

import com.young.java.examples.proxy.cglib.annotation.Transcationial;

/**
 * Created by yangyong3 on 2017/3/30.
 */
public class UserDao extends AbstractDao implements Dao {
    @Transcationial
    @Override
    public void add(String user) throws DaoException {
        System.out.println(this.getDaoSession());
        System.out.println("add user " + user);
    }

    @Transcationial
    @Override
    public void delete(String user) throws DaoException {
        System.out.println(this.getDaoSession());
        System.out.println("delete user " + user);
    }

    @Transcationial
    @Override
    public void update(String user) throws DaoException {
        System.out.println(this.getDaoSession());
        System.out.println("update user " + user);
        throw new DaoException("update error");
    }

    @Override
    public String get(String user) throws DaoException {
        System.out.println(this.getDaoSession());
        return user;
    }
}
