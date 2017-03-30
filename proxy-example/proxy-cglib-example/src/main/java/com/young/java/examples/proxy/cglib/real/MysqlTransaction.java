package com.young.java.examples.proxy.cglib.real;

/**
 * Created by yangyong3 on 2017/3/30.
 */
public class MysqlTransaction implements Transaction {
    @Override
    public void begin() {
        System.out.println("transaction begin");
    }

    @Override
    public void commit() {
        System.out.println("transaction commit");
    }

    @Override
    public void rollback() {
        System.out.println("transaction rollback");
    }
}
