package com.young.java.examples.inputformat;

import org.apache.commons.io.IOUtils;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.BytesWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.InputSplit;
import org.apache.hadoop.mapreduce.JobContext;
import org.apache.hadoop.mapreduce.RecordReader;
import org.apache.hadoop.mapreduce.TaskAttemptContext;
import org.apache.hadoop.mapreduce.lib.input.FileSplit;

import java.io.IOException;

/**
 * Created by dell on 2016/7/19.
 */
public class WholeFileRecordReader extends RecordReader<Text, BytesWritable> {

    private FileSystem fs;

    private FileSplit fileSplit;

    private JobContext jobContext;

    private Text currentKey = new Text();

    private BytesWritable currentValue;

    private boolean finishConverting = false;

    /**
     *
     * @param split 是一个大文件的一个block,或者是一个小文件
     * @param context
     * @throws IOException
     * @throws InterruptedException
     */
    @Override
    public void initialize(InputSplit split, TaskAttemptContext context) throws IOException, InterruptedException {
        this.jobContext = context;
        this.fileSplit = (FileSplit) split;
        currentKey.set(((FileSplit) split).getPath().toString());
        this.fs = FileSystem.get(context.getConfiguration());
    }

    @Override
    public boolean nextKeyValue() throws IOException, InterruptedException {
        if(!finishConverting){
            currentValue = new BytesWritable();
            int length = (int) fileSplit.getLength();
            byte[] buffer = new byte[length];
            Path file = fileSplit.getPath();
            FSDataInputStream inputStream = fs.open(file);
            IOUtils.readFully(inputStream,buffer,0,length);
            currentValue.set(buffer,0,buffer.length);
            finishConverting = true;
            return true;
        }
        return false;
    }

    @Override
    public Text getCurrentKey() throws IOException, InterruptedException {
        return currentKey;
    }

    @Override
    public BytesWritable getCurrentValue() throws IOException, InterruptedException {
        return currentValue;
    }

    @Override
    public float getProgress() throws IOException, InterruptedException {
        float progress = 0;
        if (finishConverting) {
            progress = 1;
        }
        return progress;
    }

    @Override
    public void close() throws IOException {

    }
}
