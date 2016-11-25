package com.young.java.examples.log4j2.appender.mysql;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.logging.log4j.core.Filter;
import org.apache.logging.log4j.core.Layout;
import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.core.appender.AbstractAppender;
import org.apache.logging.log4j.core.config.plugins.Plugin;
import org.apache.logging.log4j.core.config.plugins.PluginAttribute;
import org.apache.logging.log4j.core.config.plugins.PluginElement;
import org.apache.logging.log4j.core.config.plugins.PluginFactory;

import javax.sql.DataSource;
import java.io.File;
import java.io.FileInputStream;
import java.io.Serializable;
import java.util.Properties;

/**
 * Created by yangyong3 on 2016/11/25.
 */
@Plugin(name = "mysql", category = "Core", elementType = "appender", printObject = true)
public class MysqlAppender extends AbstractAppender {

    private static final String DEFAULT_TABLE_NAME = "log_oper";

    private static DataSource dataSource;

    protected MysqlAppender(String name, Filter filter, Layout<? extends Serializable> layout) {
        super(name, filter, layout);
    }

    protected MysqlAppender(String name, Filter filter, Layout<? extends Serializable> layout, boolean ignoreExceptions) {
        super(name, filter, layout, ignoreExceptions);
    }

    @PluginFactory
    public MysqlAppender createAppender(@PluginElement("Layout") Layout<? extends Serializable> layout,
                                        @PluginElement("Filter") final Filter filter,
                                        @PluginAttribute(value = "dbConfig", defaultString = "") String dbConfigPath,
                                        @PluginAttribute("name") final String name,
                                        @PluginAttribute(value = "table", defaultString = DEFAULT_TABLE_NAME) final String table,
                                        @PluginAttribute(value = "mapper", defaultString = "") final String mapperClass,
                                        @PluginAttribute(value = "ignoreExceptions", defaultBoolean = true) final boolean ignoreExceptions) {
        try {
            if (dbConfigPath == null && dbConfigPath.trim().equals("")) {
                dbConfigPath = MysqlAppender.class.getResource("/").getPath() + File.separator + "db.properties";
            }
            Properties properties = new Properties();
            properties.load(new FileInputStream(dbConfigPath));
            dataSource = new DruidDataSource();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void append(LogEvent event) {

    }
}
