package com.young.java.examples.inputformat.user;

import com.young.java.examples.writable.UserWritable;
import org.apache.hadoop.io.BytesWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.InputSplit;
import org.apache.hadoop.mapreduce.RecordReader;
import org.apache.hadoop.mapreduce.TaskAttemptContext;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;

import java.io.IOException;

/**
 * Created by dell on 2016/7/20.
 */
public class UserWritableInputFormat extends FileInputFormat<IntWritable,UserWritable> {
    @Override
    public RecordReader<IntWritable, UserWritable> createRecordReader(InputSplit split, TaskAttemptContext context) throws IOException, InterruptedException {
        RecordReader<IntWritable, UserWritable> reader = new UserWritableRecordReader();
        reader.initialize(split,context);
        return reader;
    }
}
