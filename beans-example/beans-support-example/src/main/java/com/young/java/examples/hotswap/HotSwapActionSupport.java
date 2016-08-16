package com.young.java.examples.hotswap;

import com.young.java.examples.classloader.hotswap.HotSwapAction;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.mapreduce.Job;

import java.io.IOException;

/**
 * Created by Administrator on 2016/8/14.
 */
public class HotSwapActionSupport implements HotSwapAction {
    @Override
    public void sayHello() throws IOException {
        System.out.println("hello world (version litao )");
        Configuration conf = new Configuration();
        Job job = Job.getInstance(conf);
    }

    public static void main(String[] args) throws IOException {
        HotSwapAction action = new HotSwapActionSupport();
        action.sayHello();
    }
}
