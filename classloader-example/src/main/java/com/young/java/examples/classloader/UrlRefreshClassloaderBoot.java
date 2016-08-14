package com.young.java.examples.classloader;

import com.young.java.examples.classloader.hotswap.HotSwapAction;

import java.io.File;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Administrator on 2016/8/14.
 */
public class UrlRefreshClassloaderBoot {
    public static void main(String[] args) throws Exception {
        String jarPath = "E:\\young\\java\\young-examples\\beans-example\\beans-support-example\\target\\beans-support-example-1.0.jar";
        File[] jarFiles = new File[]{new File(jarPath)};
        Set<String> filters = new HashSet<String>();
        filters.add("beans-support-example-1.0.jar");
        for(int i=0;i<10;i++) {
            ClassLoader classLoader = new UrlRefreshClassloader(jarFiles,filters);
            Class clazz = classLoader.loadClass("com.young.java.examples.hotswap.HotSwapActionSupport");
            HotSwapAction action = (HotSwapAction) clazz.newInstance();
            action.sayHello();
            Thread.sleep(5000);
        }
    }
}
