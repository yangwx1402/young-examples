package com.young.java.examples.data;

import org.apache.commons.io.FileUtils;

import java.io.File;

/**
 * Created by dell on 2016/7/20.
 */
public class RandomUserDataGenerator implements Generate {

    private int loopNum;

    private int batch;

    public RandomUserDataGenerator(int loopNum, int batch) {
        this.loopNum = loopNum;
        this.batch = batch;
    }

    @Override
    public String generate(String filePath) throws Exception {
        StringBuilder sb = new StringBuilder();
        File file = new File(filePath);
        for (int i = 0; i < loopNum; i++) {
            for (int j = 0; j < batch; j++) {
                sb.append("user_" + (j % 100) + "," + (j % 9) + "\n");
            }
            FileUtils.write(file, sb.toString(), "utf-8", true);
            sb.setLength(0);
        }
        return filePath;
    }
}
