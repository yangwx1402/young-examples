package com.young.java.examples.jdbc;

import java.sql.*;

/**
 * Created by yangyong3 on 2016/11/23.
 */
public class KylinJdbcExample {
    private static final String url = "jdbc:kylin://localhost/project";
    private static final String driver = "org.apache.kylin.jdbc.Driver";
    private static final String username = "admin";
    private static final String passwrod = "KYLIN";

    private Connection conn;

    private Connection getConnection() throws ClassNotFoundException, SQLException {
        if(conn!=null&& !conn.isClosed()) return conn;
        Class.forName(driver);
        conn = DriverManager.getConnection(url,username,passwrod);
        return conn;

    }

    public void query() throws SQLException, ClassNotFoundException {
        Connection con = getConnection();
        Statement state = con.createStatement();
        ResultSet resultSet = state.executeQuery("select * From dm_rec.DIM_LIVE_STATION limit 10");

        while (resultSet.next()) {
            System.out.println(resultSet.getObject(1));
        }
        resultSet.close();
        state.close();
        con.close();
    }
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        KylinJdbcExample example = new KylinJdbcExample();
        example.query();
    }
}
