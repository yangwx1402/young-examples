package com.young.java.example.hbase.mapreduce;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.io.ImmutableBytesWritable;
import org.apache.hadoop.hbase.mapreduce.TableMapper;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.solr.cloud.SolrZkServer;

import java.io.IOException;

/**
 * Created by dell on 2016/6/30.
 */
public class HBaseMapper extends TableMapper<NullWritable,NullWritable> {



    @Override
    protected void setup(Context context) throws IOException, InterruptedException {
        super.setup(context);
    }

    @Override
    protected void map(ImmutableBytesWritable key, Result value, Context context) throws IOException, InterruptedException {
        Configuration conf = context.getConfiguration();
        String solrUrl = conf.get("SOLR_URL");

    }
}
