package com.young.example.spider.utils;

import org.apache.commons.io.IOUtils;

import java.io.IOException;

/**
 * Created by yangyong3 on 2017/2/22.
 */
public class CommandTools {
    public static String process(String command) throws IOException, InterruptedException {
        Process process = Runtime.getRuntime().exec(command);
        int exit = process.waitFor();
        System.out.println(exit);
        return IOUtils.toString(process.getInputStream(), "utf-8");
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        String command = "D:\\dev_tools\\js\\casperjs-1.1.3\\bin\\casperjs weibo_login.js keji8023367@163.com asd16811 ./img_code.png ./img_code.txt ./result.png ./cookie.txt";
        System.out.println(CommandTools.process("D:\\dev_tools\\js\\casperjs-1.1.3\\bin\\casperjs"));
    }
}
