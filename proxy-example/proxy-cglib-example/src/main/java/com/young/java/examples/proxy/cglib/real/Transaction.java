package com.young.java.examples.proxy.cglib.real;

/**
 * Created by yangyong3 on 2017/3/30.
 */
public interface Transaction {
    void begin();

    void commit();

    void rollback();
}
