package com.young.java.examples.performance.data.support;

import com.young.java.examples.performance.data.DataGenerator;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.lib.input.CombineFileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.Set;

/**
 * Created by dell on 2016/9/28.
 */
public class RandomPhoneNumberGenerator implements DataGenerator {

    public static class RandomPhoneNumberGeneratorMapper extends Mapper<NullWritable,NullWritable,Text,Text>{

        private int recordNum = 0;

        @Override
        protected void setup(Context context) throws IOException, InterruptedException {
            Configuration configuration = context.getConfiguration();
            recordNum = Integer.parseInt(configuration.get("generator.record"),10000000);
        }

        @Override
        protected void map(NullWritable key, NullWritable value, Context context) throws IOException, InterruptedException {
            super.map(key, value, context);
        }
    }

    @Override
    public void generate(Properties config, String dist) throws Exception {
        Configuration configuration = new Configuration();
        setConfig(configuration,config);
        Job job = Job.getInstance(configuration);
        job.setJobName("RandomPhoneNumberGenerator_"+System.currentTimeMillis());
        job.setMapperClass(RandomPhoneNumberGeneratorMapper.class);
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(Text.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(Text.class);
        job.setNumReduceTasks(0);
        job.setJarByClass(RandomPhoneNumberGenerator.class);
        FileInputFormat.addInputPath(job,new Path(config.getProperty("generator.input").toString()));
        FileOutputFormat.setOutputPath(job,new Path(dist));
        job.waitForCompletion(true);
    }

    private void setConfig(Configuration configuration, Properties config) {
       Set set = config.keySet();
        for(Object key:set){
            configuration.set(key.toString(),config.getProperty(key.toString()));
        }
    }

    public static void main(String[] args) throws Exception {
        RandomPhoneNumberGenerator generator = new RandomPhoneNumberGenerator();
        Properties properties = new Properties();
        properties.load(new FileInputStream(new File(args[0])));
        generator.generate(properties,args[1]);
    }
}
