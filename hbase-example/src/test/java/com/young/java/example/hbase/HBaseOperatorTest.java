package com.young.java.example.hbase;

import com.young.java.example.hbase.support.api.HBaseAdminByApi;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Created by dell on 2016/4/22.
 */
public class HBaseOperatorTest {

    private HBaseAdminOper adminOper = null;

    public HBaseOperatorTest() throws IOException {
        Properties properties = new Properties();
        properties.load(new FileInputStream(HBaseOperatorTest.class.getResource("/").getPath()+ File.separator+"hbase.properties"));
        adminOper = new HBaseAdminByApi(properties);
    }

    public void testCreateTable() throws HBaseException {
        adminOper.createTable("young",new String[]{"name","age"});
    }

    public void testDropTable() throws HBaseException {
        adminOper.dropTable("young");
    }

    public void testAddColumn() throws HBaseException {
        adminOper.addColumnFamily("young",new String[]{"gender"});
    }

    public void testRemoveColumn() throws HBaseException {
        adminOper.removeColumnFamily("young",new String[]{"gender"});
    }

    public static void main(String[] args) throws IOException, HBaseException {
        HBaseOperatorTest test = new HBaseOperatorTest();
        test.testCreateTable();
//        test.testAddColumn();
//        test.testRemoveColumn();
//        test.testDropTable();
    }
}
