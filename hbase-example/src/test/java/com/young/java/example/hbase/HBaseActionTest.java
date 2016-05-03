package com.young.java.example.hbase;

import com.young.java.example.hbase.support.api.HBaseActionByApi;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Table;
import org.apache.hadoop.hbase.util.Bytes;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Created by dell on 2016/4/22.
 */
public class HBaseActionTest {

    private HBaseAction action = null;

    public HBaseActionTest() throws IOException {
        Properties properties = new Properties();
        properties.load(new FileInputStream(HBaseOperatorTest.class.getResource("/").getPath() + File.separator + "hbase.properties"));
        action = new HBaseActionByApi(properties);
    }

    public void addData() throws HBaseException {
        final String tableName = "young";
        final String name = "yangyong";
        final String rowKey = "1";
        final int age = 30;
       action.execute("young", new HTableCallBack() {
           @Override
           public Object callback(Table table) throws Exception {
               Put put = new Put(rowKey.getBytes());
               put.addColumn("name".getBytes(),"name".getBytes(),name.getBytes());
               put.addColumn("age".getBytes(), "age".getBytes(), Bytes.toBytes(age));
               table.put(put);
               return true;
           }
       });
    }

    public static void main(String[] args) throws IOException, HBaseException {
        HBaseActionTest test = new HBaseActionTest();
        test.addData();
    }
}
