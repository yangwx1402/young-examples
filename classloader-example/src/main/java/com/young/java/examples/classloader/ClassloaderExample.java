package com.young.java.examples.classloader;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

/**
 * Created by dell on 2016/8/12.
 */
public class ClassloaderExample {

    public void testUrlClassLoader(String jarPath) throws MalformedURLException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        File jarFile = new File(jarPath);
        ClassLoader classLoader = new URLClassLoader(new URL[]{jarFile.toURI().toURL()});
        Thread.currentThread().setContextClassLoader(classLoader);
        Class clazz = classLoader.loadClass("com.young.java.examples.beans.UserBean");
        System.out.println(clazz.newInstance());
    }

    public static void main(String[] args) throws ClassNotFoundException, MalformedURLException, InstantiationException, IllegalAccessException {
        ClassloaderExample example = new ClassloaderExample();
        example.testUrlClassLoader("E:\\project\\young\\java\\young-examples\\beans-example\\target\\beans-example-1.0.jar");
    }
}
