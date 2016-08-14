package com.young.java.examples.classloader.beans;

/**
 * Created by Administrator on 2016/8/14.
 */
public class ClassInfo {

    public ClassInfo(String className,byte[] classBytes){
        this.classBytes = classBytes;
        this.className = className;
    }

    private String className;

    private byte[] classBytes;

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public byte[] getClassBytes() {
        return classBytes;
    }

    public void setClassBytes(byte[] classBytes) {
        this.classBytes = classBytes;
    }
}
