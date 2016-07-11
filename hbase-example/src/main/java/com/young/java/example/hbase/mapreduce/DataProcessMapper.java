package com.young.java.example.hbase.mapreduce;

import com.young.examples.common.encode.EncodeUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.io.ImmutableBytesWritable;
import org.apache.hadoop.hbase.mapreduce.TableMapReduceUtil;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * Created by dell on 2016/7/1.
 */
public class DataProcessMapper extends Mapper<LongWritable, Text, Text, Put> {

    private Text mapKey = new Text();

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String content = value.toString();
        String md5 = EncodeUtils.md5(content.getBytes());
        Put put = new Put(md5.getBytes());
        mapKey.set(md5);
        put.addColumn("content".getBytes(), "info".getBytes(), content.getBytes());
        context.write(mapKey, put);
    }
}
