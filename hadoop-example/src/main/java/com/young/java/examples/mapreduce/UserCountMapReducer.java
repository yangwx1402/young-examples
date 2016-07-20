package com.young.java.examples.mapreduce;

import com.young.java.examples.inputformat.user.UserWritableInputFormat;
import com.young.java.examples.writable.UserWritable;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

/**
 * Created by dell on 2016/7/20.
 */
public class UserCountMapReducer {

    public static class UserCountMapper extends Mapper<IntWritable, UserWritable, UserWritable, IntWritable> {
        @Override
        protected void map(IntWritable key, UserWritable value, Context context) throws IOException, InterruptedException {
            context.write(value, new IntWritable(1));
        }
    }

    public static class UserCountReducer extends Reducer<UserWritable, IntWritable, UserWritable, IntWritable> {
        @Override
        protected void reduce(UserWritable key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
            int count = 0;
            for (IntWritable num : values) {
                count += num.get();
            }
            context.write(key, new IntWritable(count));
        }
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        Job job = Job.getInstance(new Configuration());
        job.setJobName("userWritableWordCount_" + System.currentTimeMillis());
        job.setMapperClass(UserCountMapper.class);
        job.setReducerClass(UserCountReducer.class);
        job.setMapOutputKeyClass(UserWritable.class);
        job.setMapOutputValueClass(IntWritable.class);
        job.setOutputKeyClass(UserWritable.class);
        job.setOutputValueClass(IntWritable.class);
        job.setCombinerClass(UserCountReducer.class);
        job.setInputFormatClass(UserWritableInputFormat.class);
        UserWritableInputFormat.addInputPath(job,new Path(args[0]));
        FileOutputFormat.setOutputPath(job,new Path(args[1]));
        job.setNumReduceTasks(3);
        job.setJarByClass(UserCountMapReducer.class);
        job.waitForCompletion(true);
    }
}
