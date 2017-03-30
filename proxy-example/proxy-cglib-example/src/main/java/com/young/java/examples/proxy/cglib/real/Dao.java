package com.young.java.examples.proxy.cglib.real;

/**
 * Created by yangyong3 on 2017/3/30.
 */
public interface Dao {

    void add(String user) throws DaoException;

    void delete(String user) throws DaoException;

    void update(String user) throws DaoException;

    String get(String user) throws DaoException;


}
