package com.young.java.examples.log4j2.appender.hbase;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.Put;
import org.apache.logging.log4j.core.Filter;
import org.apache.logging.log4j.core.Layout;
import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.core.appender.AbstractAppender;
import org.apache.logging.log4j.core.appender.AppenderLoggingException;
import org.apache.logging.log4j.core.config.plugins.Plugin;
import org.apache.logging.log4j.core.config.plugins.PluginAttribute;
import org.apache.logging.log4j.core.config.plugins.PluginElement;
import org.apache.logging.log4j.core.config.plugins.PluginFactory;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Created by yangyong3 on 2016/11/25.
 */
@Plugin(name = "HBase", category = "Core", elementType = "appender", printObject = true)
public class HBaseAppender extends AbstractAppender {

    private static Configuration configuration;

    private static final String DEFAULT_TABLE_NAME = "log_oper";

    private static Connection connection;

    private final ReadWriteLock rwLock = new ReentrantReadWriteLock();

    private final Lock readLock = rwLock.readLock();

    private static String tableName;

    private static HBasePutGenerator putGenerator;

    protected HBaseAppender(String name, Filter filter, Layout<? extends Serializable> layout) {
        //ConsoleAppender
        super(name, filter, layout);
    }

    protected HBaseAppender(String name, Filter filter, Layout<? extends Serializable> layout, boolean ignoreExceptions) {
        super(name, filter, layout, ignoreExceptions);
    }

    @PluginFactory
    public static HBaseAppender createAppender(@PluginElement("Layout") Layout<? extends Serializable> layout,
                                               @PluginElement("Filter") final Filter filter,
                                               @PluginAttribute(value = "zookeeper") String zookeeper,
                                               @PluginAttribute("name") final String name,
                                               @PluginAttribute(value = "table", defaultString = DEFAULT_TABLE_NAME) final String table,
                                               @PluginAttribute(value = "putClass", defaultString = "") final String putClass,
                                               @PluginAttribute(value = "hbaseConfig", defaultString = "") final String hbaseConfigPath,
                                               @PluginAttribute(value = "ignoreExceptions", defaultBoolean = true) final boolean ignoreExceptions) {
        try {
            if (configuration == null) {
                configuration = HBaseConfiguration.create();
                if (hbaseConfigPath != null && !hbaseConfigPath.trim().equals(""))
                    configuration.addResource(new FileInputStream(hbaseConfigPath));
            }
            if (putClass == null || putClass.trim().equals("")) {
                putGenerator = new DefaultHBasePutGenerator();
            } else {
                putGenerator = (HBasePutGenerator) Class.forName(putClass).newInstance();
            }
            if (table == null || table.trim().equals("")) {
                tableName = DEFAULT_TABLE_NAME;
            } else {
                tableName = table;
            }
            connection = ConnectionFactory.createConnection(configuration);
        } catch (Exception e) {
            if (!ignoreExceptions)
                throw new AppenderLoggingException(e);
        }
        return new HBaseAppender(name, filter, layout, ignoreExceptions);
    }

    @Override
    public void append(LogEvent logEvent) {
        readLock.lock();
        try {
            final byte[] bytes = getLayout().toByteArray(logEvent);
            String string = new String(bytes, "utf-8");
            writeToHBase(string);
        } catch (Exception ex) {
            if (!ignoreExceptions()) {
                throw new AppenderLoggingException(ex);
            }
        } finally {
            readLock.unlock();
        }
    }

    private void writeToHBase(String string) throws IOException {
        HTable table = (HTable) connection.getTable(TableName.valueOf(tableName));
        Put put = putGenerator.getPut(string);
        if (put != null) {
            table.put(put);
        }
    }
}
