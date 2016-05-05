package com.young.java.examples.testcase.boot;

import com.young.java.examples.testcase.annotations.PackageScan;
import com.young.java.examples.testcase.annotations.TestCase;
import com.young.java.examples.testcase.common.Config;
import com.young.java.examples.testcase.example.TestConfig;
import org.apache.commons.collections.CollectionUtils;

import java.io.File;
import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/5/5.
 */
public class AnnotationBoot {
    /**
     * @param clazz
     */

    private boolean checkConfigInterface(Class clazz) {
        Class[] classes = clazz.getInterfaces();
        boolean flag = false;
        for (Class cl : classes) {
            if (cl == Config.class) {
                flag = true;
            }
        }
        return flag;
    }

    public void boot(Class<? extends Config>[] classes) throws Exception {
        if (classes == null || classes.length == 0)
            return;
        for (Class clazz : classes) {
            if (checkConfigInterface(clazz)) {
                process(clazz);
            } else {
                throw new Exception(clazz.getName() + " is not implements Config");
            }
        }
    }

    private void process(Class clazz) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, InstantiationException, ClassNotFoundException {
        Annotation annotation = clazz.getAnnotation(PackageScan.class);
        Method method = annotation.getClass().getMethod("path");
        String path = (String) method.invoke(annotation);
        List<String> classes = scanClasses(path);
        if(!CollectionUtils.isEmpty(classes)) {
            for (String str : classes) {
                invokeTest(str);
            }
        }
    }

    private void invokeTest(String str) throws ClassNotFoundException, IllegalAccessException, InstantiationException, InvocationTargetException, NoSuchMethodException {
        Class clazz = Class.forName(str);
        Annotation annotation = clazz.getAnnotation(TestCase.class);
        Method method = annotation.getClass().getMethod("value");
        String name = (String) method.invoke(annotation);
        Object obj = clazz.newInstance();
        Method[] methods = clazz.getDeclaredMethods();
        if(methods!=null&&methods.length>0){
            long start = System.currentTimeMillis();
            System.out.println("begining test "+name);
            for(Method m:methods){
                m.invoke(obj);
            }
            System.out.println("end test "+name+",cost time "+(System.currentTimeMillis()-start));
        }
    }

    private List<String> scanClasses(String path) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        List<String> classes = new ArrayList<String>();
        String dir = AnnotationBoot.class.getResource("/").getPath();
        String classPath = dir + path.replace('.', File.separatorChar);
        System.out.println(classPath);
        File[] files = new File(classPath).listFiles();
        for (File f : files) {
            String className = path + "." + f.getName();
            classes.add(className.substring(0, className.length() - 6));
        }
        return classes;
    }

    public static void main(String[] args) throws Exception {
        AnnotationBoot boot = new AnnotationBoot();
        boot.boot(new Class[]{TestConfig.class});
    }
}
