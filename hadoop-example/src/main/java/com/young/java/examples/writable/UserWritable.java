package com.young.java.examples.writable;

import org.apache.hadoop.io.WritableComparable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

/**
 * Created by Administrator on 2016/7/19.
 */
public class UserWritable implements WritableComparable<UserWritable> {

    private String username;

    private Integer age;

    public String getUsername() {
        return username;
    }

    public Integer getAge() {
        return age;
    }

    public void setValue(String username, Integer age) {
        this.username = username;
        this.age = age;
    }

    @Override
    public int compareTo(UserWritable o) {
        if (o.getUsername().compareTo(username) == 0) {
            return o.getAge().compareTo(age);
        } else
            return o.getUsername().compareTo(username);
    }

    @Override
    public void write(DataOutput dataOutput) throws IOException {
        dataOutput.writeUTF(username);
        dataOutput.writeInt(age);

    }

    @Override
    public void readFields(DataInput dataInput) throws IOException {
        this.username = dataInput.readUTF();
        this.age = dataInput.readInt();
    }

    public String toString(){
        return "[username="+username+",age="+age+"]";
    }
}
