package com.young.java.examples.inputformat;

import org.apache.hadoop.io.BytesWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.InputSplit;
import org.apache.hadoop.mapreduce.RecordReader;
import org.apache.hadoop.mapreduce.TaskAttemptContext;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;

import java.io.IOException;

/**
 * Created by dell on 2016/7/19.
 */
public class WholeFileInputFormat extends FileInputFormat<Text,BytesWritable> {
    @Override
    public RecordReader<Text, BytesWritable> createRecordReader(InputSplit split, TaskAttemptContext context) throws IOException, InterruptedException {
        RecordReader<Text,BytesWritable> reader = new WholeFileRecordReader();
        reader.initialize(split,context);
        return reader;
    }
}
