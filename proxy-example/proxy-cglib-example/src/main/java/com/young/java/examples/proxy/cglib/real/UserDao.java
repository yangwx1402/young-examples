package com.young.java.examples.proxy.cglib.real;

/**
 * Created by yangyong3 on 2017/3/30.
 */
public class UserDao implements Dao {
    @Override
    public void add(String user) throws DaoException {
        System.out.println("add user " + user);
    }

    @Override
    public void delete(String user) throws DaoException {
        System.out.println("delete user " + user);
    }

    @Override
    public void update(String user) throws DaoException {
        System.out.println("update user " + user);
        throw new DaoException("update error");
    }

    @Override
    public String get(String user) throws DaoException {
        return user;
    }
}
