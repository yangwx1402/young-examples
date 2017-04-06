package com.young.java.examples.classloader.classselectclassloader;

import java.io.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by young on 2016/8/14.
 * 可以自动刷新的类加载器,并且可以指定哪些类由该类加载器进行加载
 */
public class RefreshClassloader extends ClassLoader{
    /**
     * classpath目录,也就是class文件的根目录
     */
    private String loadBaseDir;
    /**
     * 选择通过该类加载器加载的类,该Set中的类名都由该类加载器加载
     */
    private Set<String> classNames;


    /**
     * 构造方法
     * @param loadBaseDir
     * @param clazzes
     * @throws IOException
     */
    public RefreshClassloader(String loadBaseDir,String[] clazzes) throws IOException {
        /**
         * 为了防止类加载器的向上加载机制,所以父加载器为null
         */
        super(null);
        this.loadBaseDir = loadBaseDir;
        classNames = new HashSet<String>();
        /**
         * 加载由该类加载器加载的类
         */
        loadClassByMe(clazzes);
    }

    /**
     * 从字节码中加载Class
     * @param className 类全限定名
     * @param classInputStream 字节码文件流
     * @param length 字节码文件大小
     * @return Class对象
     * @throws IOException
     */
    private Class initClass(String className,InputStream classInputStream,long length) throws IOException {
        byte[] classBytes = new byte[(int) length];
        classInputStream.read(classBytes);
        classInputStream.close();
        return defineClass(className,classBytes,0,classBytes.length);
    }

    /**
     * 实现ClassLoader类中的loadClass,加载类都是通过该方法进行
     * @param name
     * @param resolve
     * @return
     * @throws ClassNotFoundException
     */
    @Override
    protected Class<?> loadClass(String name, boolean resolve) throws ClassNotFoundException {
        Class clazz = null;
        clazz = findLoadedClass(name);
        /**
         * 如果类名不在该类加载器加载的范围中,那么就采用系统类加载器进行加载
         */
        if(!classNames.contains(name)&&clazz == null){
            clazz = getSystemClassLoader().loadClass(name);
        }
        if(clazz == null){
            throw new ClassNotFoundException(name);
        }
        if(resolve){
            resolveClass(clazz);
        }
        return clazz;
    }

    /**
     * 根据类全限定名对类进行装载
     * @param name
     * @return
     * @throws IOException
     */
    private Class loadClassFromClassPath(String name) throws IOException {
       Class clazz = null;
        StringBuffer buffer = new StringBuffer(loadBaseDir);
        String classname = name.replace('.', File.separatorChar) + ".class";
        buffer.append(File.separator+classname);
        File classFile = new File(buffer.toString());
        clazz = initClass(name,new FileInputStream(classFile),classFile.length());
        return clazz;
    }

    /**
     * 加载客户端提供的需要加载的类
     * @param clazznames
     * @throws IOException
     */
    private void loadClassByMe(String[] clazznames) throws IOException {
        for (int i = 0; i < clazznames.length; i++) {
            loadClassFromClassPath(clazznames[i]);
            classNames.add(clazznames[i]);
        }
    }
}
