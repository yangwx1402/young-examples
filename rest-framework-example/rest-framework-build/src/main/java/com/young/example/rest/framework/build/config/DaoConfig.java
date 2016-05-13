package com.young.example.rest.framework.build.config;

import org.springframework.context.annotation.*;
import org.springframework.core.io.support.ResourcePropertySource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.io.IOException;

/**
 * Created by dell on 2016/4/5.
 */
@Configuration
@ComponentScan("com.young.java.examples.dao")
@PropertySource(name = "db", value = {"classpath:db/db.properties"})
@ImportResource("classpath:spring/mybatis.xml")
public class DaoConfig {

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        try {
            ResourcePropertySource env = new ResourcePropertySource("resource",
                    "classpath:db/db.properties");
            org.springframework.util.Assert.notNull(env, "数据库环境信息不可以为空");
            dataSource.setDriverClassName((String) env
                    .getProperty("jdbc.driverClassName"));
            dataSource.setUrl((String) env.getProperty("jdbc.url"));
            dataSource.setUsername((String) env.getProperty("jdbc.username"));
            dataSource.setPassword((String) env.getProperty("jdbc.password"));
        } catch (IOException e) {
            throw new RuntimeException("读取数据库配置文件出错");
        }
        org.apache.ibatis.logging.LogFactory.useLog4JLogging();
        return dataSource;
    }
}
