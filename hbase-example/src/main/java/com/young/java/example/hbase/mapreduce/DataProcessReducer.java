package com.young.java.example.hbase.mapreduce;

import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.io.ImmutableBytesWritable;
import org.apache.hadoop.hbase.mapreduce.TableReducer;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

/**
 * Created by dell on 2016/7/1.
 */
public class DataProcessReducer extends TableReducer<Text,Put,ImmutableBytesWritable> {

    @Override
    protected void reduce(Text key, Iterable<Put> values, Context context) throws IOException, InterruptedException {
        ImmutableBytesWritable reduceKey = new ImmutableBytesWritable(key.toString().getBytes());
        for(Put put:values){
            context.write(reduceKey,put);
        }
    }
}
