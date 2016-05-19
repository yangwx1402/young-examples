package com.young.java.examples.druid;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by dell on 2016/5/18.
 */
public class DruidDataSourceExample {

    public void test() throws SQLException {
        ApplicationContext ac = new ClassPathXmlApplicationContext("/spring/druid-spring.xml");
        DataSource dataSource = (DataSource) ac.getBean("dataSource");
        Connection connection = dataSource.getConnection();
        Statement stmt = connection.createStatement();
        String sql = "select * from bigdata_file_details";
        ResultSet rs = stmt.executeQuery(sql);
        while(rs.next()){
            System.out.println(rs.getString(1));
        }
        rs.close();
        stmt.close();
        connection.close();
    }

    public static void main(String[] args) throws SQLException {
        DruidDataSourceExample example = new DruidDataSourceExample();
        example.test();
    }
}
