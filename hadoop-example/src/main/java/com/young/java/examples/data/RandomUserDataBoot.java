package com.young.java.examples.data;

import java.io.File;

/**
 * Created by dell on 2016/7/20.
 */
public class RandomUserDataBoot {
    public static void main(String[] args) {
        String path = "E:\\data\\user";
        int loop = 1000;
        int batch = 1000;
        for(int i=0;i<10;i++){
            new Thread(new GenerateData(path+ File.separator+""+i+".tmp",new RandomUserDataGenerator(loop,batch))).start();
        }
    }
}
