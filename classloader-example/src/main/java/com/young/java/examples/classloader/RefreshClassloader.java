package com.young.java.examples.classloader;

import java.io.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Administrator on 2016/8/14.
 */
public class RefreshClassloader extends ClassLoader{
    private String loadBaseDir;
    private Set<String> classNames;


    public RefreshClassloader(String loadBaseDir,String[] clazzes) throws IOException {
        super(null);
        this.loadBaseDir = loadBaseDir;
        classNames = new HashSet<String>();
        loadClassByMe(clazzes);
    }

    private Class initClass(String className,InputStream classInputStream,long length) throws IOException {
        byte[] classBytes = new byte[(int) length];
        classInputStream.read(classBytes);
        classInputStream.close();
        return defineClass(className,classBytes,0,classBytes.length);
    }

    @Override
    protected Class<?> loadClass(String name, boolean resolve) throws ClassNotFoundException {
        Class clazz = null;
        clazz = findLoadedClass(name);
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

    private Class loadClassFromClassPath(String name) throws IOException {
       Class clazz = null;
        StringBuffer buffer = new StringBuffer(loadBaseDir);
        String classname = name.replace('.', File.separatorChar) + ".class";
        buffer.append(File.separator+classname);
        File classFile = new File(buffer.toString());
        clazz = initClass(name,new FileInputStream(classFile),classFile.length());
        return clazz;
    }

    private void loadClassByMe(String[] clazznames) throws IOException {
        for (int i = 0; i < clazznames.length; i++) {
            loadClassFromClassPath(clazznames[i]);
            classNames.add(clazznames[i]);
        }
    }
}
