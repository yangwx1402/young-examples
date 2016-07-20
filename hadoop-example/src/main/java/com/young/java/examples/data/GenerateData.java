package com.young.java.examples.data;

/**
 * Created by dell on 2016/7/20.
 */
public class GenerateData implements Runnable {

    private String fileName;

    private Generate generate;

    public GenerateData(String fileName, Generate generate) {
        this.fileName = fileName;
        this.generate = generate;
    }

    @Override
    public void run() {
        try {
            generate.generate(fileName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
