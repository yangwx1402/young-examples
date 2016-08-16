package com.young.java.examples.classloader.isolationclassloader;

import com.young.java.examples.classloader.ClassLoaderBootUtils;

import java.io.File;
import java.net.URL;

/**
 * Created by dell on 2016/8/16.
 */
public class IsolationClassLoaderDemo {
    public static void main(String[] args) throws Exception {
        File dependPath = new File("E:\\project\\young\\java\\young-examples\\beans-example\\beans-support-example\\lib");
        File[] jarfiles = dependPath.listFiles();
        URL[] urls = new URL[jarfiles.length];
        for (int i=0;i<jarfiles.length;i++){
            urls[i] = jarfiles[i].toURI().toURL();
        }
        for (int i = 0; i < 10; i++) {
            ClassLoader classLoader = new IsolationClassLoader(urls);
            ClassLoaderBootUtils.invokeMain(classLoader.loadClass("com.young.java.examples.hotswap.HotSwapActionSupport"));
            Thread.sleep(10000);
        }
    }
}
