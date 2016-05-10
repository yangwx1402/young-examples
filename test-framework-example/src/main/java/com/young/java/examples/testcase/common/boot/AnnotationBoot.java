package com.young.java.examples.testcase.common.boot;

import com.young.java.examples.testcase.common.annotations.PackageScan;
import com.young.java.examples.testcase.common.process.AnnotationProcess;
import com.young.java.examples.testcase.example.testframework.annotations.TestCase;
import com.young.java.examples.testcase.common.config.Config;
import com.young.java.examples.testcase.example.testframework.config.TestConfig;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

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

    public void boot(Class<? extends Config>[] classes,List<AnnotationProcess> processes) throws Exception {
        if (classes == null || classes.length == 0)
            return;
        for (Class clazz : classes) {
            if (checkConfigInterface(clazz)) {
                process(clazz,processes);
            } else {
                throw new Exception(clazz.getName() + " is not implements Config");
            }
        }
    }

    private void process(Class clazz, List<AnnotationProcess> processes) throws Exception {
        Annotation annotation = clazz.getAnnotation(PackageScan.class);
        Method method = annotation.getClass().getMethod("path");
        String path = (String) method.invoke(annotation);
        List<String> classes = scanClasses(path);
        if (!CollectionUtils.isEmpty(classes)) {
            for (String str : classes) {
                invokeTest(str, processes);
            }
        }
    }

    private void invokeTest(String str, List<AnnotationProcess> processes) throws Exception {
        Class clazz = Class.forName(str);
        if(!CollectionUtils.isEmpty(processes)){
            for(AnnotationProcess process:processes){
                process.process(clazz);
            }
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
}
