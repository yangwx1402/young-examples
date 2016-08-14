package com.young.java.examples.classloader.urlclassloader;

import com.young.java.examples.classloader.beans.ClassInfo;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

/**
 * Created by young on 2016/8/14.
 */
public class UrlRefreshClassloader extends ClassLoader {

    /**
     * 需要通过该类加载器加载的jar包文件名
     */
    private Set<String> loadClassPrefix;

    /**
     * 保存類加載信息的倒排索引,用來檢索需要加載的類通過哪個類加載器加載
     */
    private Map<String,String> allClasses = new HashMap<String,String>();

    public UrlRefreshClassloader(File[] jarFiles,Set<String> loadJarNames) throws Exception {
        super(null);
        this.loadClassPrefix = loadJarNames;
        /**
         * 加載所有的第三方jar包中的類
         */
        for(File file:jarFiles){
            loadClassByRefresh(file);
        }
    }

    /**
     * 加载jar包中的类
     * @param jarFile jar文件
     * @throws Exception
     */
    private void loadClassByRefresh(File jarFile) throws Exception {
            FileInputStream fis = new FileInputStream(jarFile);
            List<ClassInfo> jarClassResult = JarFileTools.unzipJarFile(fis);
            fis.close();
            String classname = null;
            for(ClassInfo entry:jarClassResult){
                classname = entry.getClassName().replace('/', '.').replace(".class","");
                loadClassFromClassPath(classname,entry.getClassBytes());
                allClasses.put(entry.getClassName(),jarFile.getName());
            }
    }

    /**
     * 通过字节码加载Class
     * @param className 类全限定名
     * @param bytes  字节码
     * @return Class
     * @throws IOException
     */
    private Class initClass(String className,byte[] bytes) throws IOException {
        return defineClass(className,bytes,0,bytes.length);
    }

    /**
     * 根据类名和字节码加载类
     * @param clazzname
     * @param bytes
     * @return
     * @throws IOException
     */
    private Class loadClassFromClassPath(String clazzname,byte[] bytes) throws IOException {
        Class clazz = null;
        clazz = initClass(clazzname,bytes);
        return clazz;
    }

    /**
     * 重写ClassLoader的loadClass方法进行类的加载
     * @param name
     * @param resolve
     * @return
     * @throws ClassNotFoundException
     */
    @Override
    protected Class<?> loadClass(String name, boolean resolve) throws ClassNotFoundException {
        Class clazz = null;
        clazz = findLoadedClass(name);
        //不在要加载jar包中的类,通过系统加载器进行加载
        if(!allClasses.containsKey(name)&&clazz == null){
           clazz = getSystemClassLoader().loadClass(name);
        //虽然在要加载的jar包中,但是jar包名字不在要加载的jar文件名的Set中,也采用系统加载器进行加载
        }else if(!loadClassPrefix.contains(allClasses.get(name))&&clazz ==null){
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
}
