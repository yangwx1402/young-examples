package com.young.java.examples.classloader;

import com.young.java.examples.classloader.hotswap.HotSwapAction;

import java.io.IOException;

/**
 * Created by Administrator on 2016/8/14.
 */
public class RefreshClassloaderBoot {
    public static void main(String[] args) throws IOException, ClassNotFoundException, IllegalAccessException, InstantiationException, InterruptedException {
        String baseDir = "E:\\young\\java\\young-examples\\beans-example\\beans-support-example\\target\\classes";
        String[] classes = new String[]{"com.young.java.examples.hotswap.HotSwapActionSupport"};
        for(int i=0;i<10;i++) {
            ClassLoader classLoader = new RefreshClassloader(baseDir, classes);
            Class clazz = classLoader.loadClass("com.young.java.examples.hotswap.HotSwapActionSupport");
            HotSwapAction action = (HotSwapAction) clazz.newInstance();
            action.sayHello();
            Thread.sleep(5000);
        }
    }
}
