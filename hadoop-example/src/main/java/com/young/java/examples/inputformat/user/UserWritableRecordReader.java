package com.young.java.examples.inputformat.user;

import com.young.java.examples.writable.UserWritable;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.io.BytesWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.InputSplit;
import org.apache.hadoop.mapreduce.RecordReader;
import org.apache.hadoop.mapreduce.TaskAttemptContext;
import org.apache.hadoop.mapreduce.lib.input.FileSplit;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by dell on 2016/7/20.
 */
public class UserWritableRecordReader extends RecordReader<IntWritable, UserWritable> {

    private IntWritable currentKey;

    private UserWritable currentValue;

    private FileSystem fs;

    private BufferedReader br;

    private boolean hasNext = true;

    private String line;

    @Override
    public void initialize(InputSplit split, TaskAttemptContext context) throws IOException, InterruptedException {
        System.out.println("initialize recordReader--------------");
        fs = FileSystem.get(context.getConfiguration());
        br = new BufferedReader(new InputStreamReader(fs.open(((FileSplit) split).getPath())));
    }

    @Override
    public boolean nextKeyValue() throws IOException, InterruptedException {
        line = br.readLine();
        System.out.println("line--------------------"+line);
        if (line == null) {
            hasNext = false;
        } else {
            if (!line.trim().equals("")) {
                String[] temp = line.split(",");
                if (temp != null && temp.length == 2) {
                    this.currentKey = new IntWritable(Integer.parseInt(temp[1]));
                    this.currentValue = new UserWritable();
                    this.currentValue.setValue(temp[0], Integer.parseInt(temp[1]));
                }
            }
        }
        return hasNext;
    }

    @Override
    public IntWritable getCurrentKey() throws IOException, InterruptedException {
        return this.currentKey;
    }

    @Override
    public UserWritable getCurrentValue() throws IOException, InterruptedException {
        return this.currentValue;
    }

    @Override
    public float getProgress() throws IOException, InterruptedException {
        if (!hasNext) {
            return 1.0f;
        }
        return 0;
    }

    @Override
    public void close() throws IOException {
        br.close();
    }
}
