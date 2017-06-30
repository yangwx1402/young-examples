package com.young.java.examples.pattern.structure.flyweight;

import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * Created by yangyong3 on 2017/6/30.
 * 享元模式,其实就相当于在内部构造了一个缓冲，获取一个对象的时候先从缓存中获取。
 */
public class ConnectionPool {
    private final BlockingDeque<Connection> pool;

    public ConnectionPool() {
        this(10);
    }

    public ConnectionPool(int poolSize) {
        pool = new LinkedBlockingDeque<>(poolSize);
        for (int i = 0; i < poolSize; i++) {
            pool.push(new Connection());
        }
    }

    public Connection getConnection() throws Exception {
        if (pool != null && pool.size() > 0) {
            Connection con =  pool.pop();
            con.setClose(false);
            return con;
        }
        throw new Exception("connection is use up");
    }

    public void close(Connection connection) {
        if (pool != null) {
            if (!connection.isClose()) {
                connection.setClose(true);
                pool.push(connection);
            }
        }
    }
}
