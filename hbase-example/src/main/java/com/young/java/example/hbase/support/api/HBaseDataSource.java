package com.young.java.example.hbase.support.api;

import com.young.java.example.hbase.HBaseException;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;

import java.io.IOException;
import java.util.Map;
import java.util.Properties;

/**
 * Created by dell on 2016/4/22.
 */
public class HBaseDataSource {
    private Connection connection;

    private Properties properties;

    public HBaseDataSource() {
        this(null);
    }

    public HBaseDataSource(Properties properties) {
        this.properties = properties;
    }

    private void checkConnection() throws HBaseException {
        Configuration configuration = HBaseConfiguration.create();
        if (properties != null) {
            for (Map.Entry entry : properties.entrySet()) {
                configuration.set(entry.getKey().toString(), entry.getValue().toString());
            }
        }
        if (connection == null || connection.isClosed())
            try {
                connection = ConnectionFactory.createConnection(configuration);
            } catch (IOException e) {
                e.printStackTrace();
                throw new HBaseException("创建HBase Connection失败!",e);
            }
    }

    public Connection getConnection() throws HBaseException {
        checkConnection();
        return connection;
    }
}
