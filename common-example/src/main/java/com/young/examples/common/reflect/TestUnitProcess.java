package com.young.examples.common.reflect;

/**
 * Created by Administrator on 2016/3/24.
 */

import java.io.File;

/**
 *
 */
public class TestUnitProcess {

    private String packageName;

    public TestUnitProcess(){
        this.packageName = TestUnitProcess.class.getPackage().getName();
    }

    public TestUnitProcess(String packageName){
        this.packageName = packageName;
    }

    public void process() throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        String dir = TestUnitProcess.class.getResource("/").getPath();
        String classPath = dir+packageName.replace('.', File.separatorChar);
        System.out.println(classPath);
        File[] files = new File(classPath).listFiles();
        for(File f:files){
            System.out.println(packageName+"."+f.getName());
            String className = packageName+"."+f.getName();
            System.out.println(Class.forName(className.substring(0,className.length()-6)).newInstance());
        }
    }

    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        TestUnitProcess process = new TestUnitProcess();
        process.process();
    }
}
