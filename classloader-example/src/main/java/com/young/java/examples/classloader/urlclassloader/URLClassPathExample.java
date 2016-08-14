package com.young.java.examples.classloader.urlclassloader;

import sun.misc.URLClassPath;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by young on 2016/8/14.
 */
public class URLClassPathExample {
    public static void main(String[] args) throws MalformedURLException {
        String jarPath = "E:\\young\\java\\young-examples\\beans-example\\beans-support-example\\target\\beans-support-example-1.0.jar";
        String clazzname = "com.young.java.examples.hotswap.HotSwapActionSupport";
        String path = clazzname.replace('.', File.separatorChar) + ".class";
        URL[] urls = new URL[]{new File(jarPath).toURI().toURL()};
        URLClassPath classPath = new URLClassPath(urls);
        System.out.println(classPath.getResource(new File(jarPath).toURI().toURL().toString()));
        System.out.println();

    }
}
