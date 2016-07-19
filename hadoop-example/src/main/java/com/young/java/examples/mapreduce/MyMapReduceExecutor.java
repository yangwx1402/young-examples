package com.young.java.examples.mapreduce;

import com.isoftstone.bigdata.api.workflow.node.actionnode.mr.AbstractMRJobExecutor;
import com.isoftstone.bigdata.api.workflow.node.actionnode.mr.domain.MRExecuteDomain;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.mapreduce.Job;

/**
 * Created by dell on 2016/6/29.
 */
public class MyMapReduceExecutor extends AbstractMRJobExecutor {
    @Override
    public void before() {

    }

    @Override
    public Job execute(Configuration configuration, Job job, MRExecuteDomain mrExecuteDomain) {
        return null;
    }

    @Override
    public String getOutPut() {
        return null;
    }

    @Override
    public void after() {

    }
}
