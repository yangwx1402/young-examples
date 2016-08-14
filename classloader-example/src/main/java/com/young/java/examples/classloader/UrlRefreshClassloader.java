package com.young.java.examples.classloader;

import com.young.java.examples.classloader.beans.ClassInfo;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

/**
 * Created by Administrator on 2016/8/14.
 */
public class UrlRefreshClassloader extends ClassLoader {

    private Set<String> loadClassPrefix;

    /**
     * 保存類加載信息的倒排索引,用來檢索需要加載的類通過哪個類加載器加載
     */
    private Map<String,String> allClasses = new HashMap<String,String>();

    public UrlRefreshClassloader(File[] jarFiles,Set<String> loadJarNames) throws Exception {
        super(null);
        loadClassPrefix = new HashSet<String>();
        /**
         * 加載所有的第三方jar包中的類
         */
        for(File file:jarFiles){
            loadClassByRefresh(file);
        }
    }

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

    private Class initClass(String className,byte[] bytes) throws IOException {
        return defineClass(className,bytes,0,bytes.length);
    }

    private Class loadClassFromClassPath(String clazzname,byte[] bytes) throws IOException {
        Class clazz = null;
        clazz = initClass(clazzname,bytes);
        return clazz;
    }

    @Override
    protected Class<?> loadClass(String name, boolean resolve) throws ClassNotFoundException {
        Class clazz = null;
        clazz = findLoadedClass(name);
        if(!allClasses.containsKey(name)&&clazz == null){
           clazz = getSystemClassLoader().loadClass(name);
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
