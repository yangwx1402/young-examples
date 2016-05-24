package com.young.java.examples.hadoop;

import com.young.examples.common.encode.EncodeUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

import java.io.*;

/**
 * Created by dell on 2016/5/24.
 */
public class HdfsTools {

    private FileSystem fileSystem;

    public HdfsTools() throws IOException {
        fileSystem = FileSystem.get(new Configuration());
    }

    public void put(String localFile, String hdfsFile) {
        BufferedReader br = null;
        PrintWriter pw = null;
        String line = null;
        try {
            br = new BufferedReader(new InputStreamReader(new FileInputStream(localFile), "utf-8"));
            pw = new PrintWriter(fileSystem.create(new Path(hdfsFile), true));
            while((line=br.readLine())!=null){
                pw.write(EncodeUtils.base64Encode(line));
                pw.write("\n");
            }
            pw.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                br.close();
                pw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void get(String hdfsFile, String localFile) {
        BufferedReader br = null;
        PrintWriter pw = null;
        String line = null;
        try{
            br = new BufferedReader(new InputStreamReader(fileSystem.open(new Path(hdfsFile)), "utf-8"));
            pw = new PrintWriter(new FileOutputStream(localFile));
            while((line=br.readLine())!=null){
                pw.write(EncodeUtils.base64Decode(line));
                pw.write("\n");
            }
            pw.flush();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                br.close();
                pw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws IOException {
        if(args.length<3){
            System.out.println("this program needs 3 params");
            System.exit(-1);
        }
        String type = args[0];
        HdfsTools hdfs = new HdfsTools();
        if("get".equals(type.trim())){
            String hdfsFile = args[1];
            String localFile = args[2];
            hdfs.get(hdfsFile,localFile);
        }else if("put".equals(type.trim())){
            String localFile = args[1];
            String hdfsFile = args[2];
            hdfs.put(localFile,hdfsFile);
        }
    }
}
