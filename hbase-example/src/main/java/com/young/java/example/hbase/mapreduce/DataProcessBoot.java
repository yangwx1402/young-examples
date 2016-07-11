package com.young.java.example.hbase.mapreduce;

import com.isoftstone.bigdata.api.workflow.node.actionnode.mr.AbstractMRJobExecutor;
import com.isoftstone.bigdata.api.workflow.node.actionnode.mr.domain.MRExecuteDomain;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.io.ImmutableBytesWritable;
import org.apache.hadoop.hbase.mapreduce.TableMapReduceUtil;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;

import java.io.IOException;

/**
 * Created by dell on 2016/7/1.
 */
public class DataProcessBoot extends AbstractMRJobExecutor {
    @Override
    public void before() {
        System.out.println("啥也不干");
    }

    @Override
    public Job execute(Configuration configuration, Job job, MRExecuteDomain mrExecuteDomain) {
        job.setJobName("processData_" + System.currentTimeMillis());
        job.setMapperClass(DataProcessMapper.class);
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(Put.class);
        job.setOutputKeyClass(ImmutableBytesWritable.class);
        job.setOutputValueClass(Put.class);
        job.setJarByClass(DataProcessBoot.class);
        try {
            TableMapReduceUtil.initTableReducerJob("content_info",DataProcessReducer.class,job);
            FileInputFormat.addInputPath(job,new Path("/content_info/input"));
            return job;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String getOutPut() {
        return "/content_info/input";
    }

    @Override
    public void after() {

        System.out.println("啥也不干");
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        //create 'content_info',''
        System.setProperty("HADOOP_USER_NAME","root");
        DataProcessBoot boot = new DataProcessBoot();
        Configuration conf = new Configuration();
        conf.setBoolean("mapreduce.app-submission.cross-platform", true);
        Configuration conf2 = HBaseConfiguration.create();
        conf.addResource(conf2);
        Job job = Job.getInstance(conf);
        MRExecuteDomain domain = new MRExecuteDomain();
        job = boot.execute(conf,job,domain);
        job.waitForCompletion(true);
    }
}
