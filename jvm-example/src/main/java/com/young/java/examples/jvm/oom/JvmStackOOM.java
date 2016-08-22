package com.young.java.examples.jvm.oom;

/**
 * Created by young.yang on 2016/8/20.
 * 测试虚拟机栈和本地方法栈溢出
 * 由于Hotspot虚拟机中并不区分虚拟机栈和本地方法栈,所以-Xoss(修改本地方法栈)无效,统一由-Xss参数来设置
 * 该区会有两种溢出抛出1.超过栈最大深度 StackOverflowError 2.无法申请内存 OutOfMemoryError
 * 参数设置-Xss128k   我们来用递归来测试
 */
public class JvmStackOOM {

    private int stackLength = 1;

    /**
     * 这里通常会爆出java.lang.StackOverflowError,除非在setStackLength方法中使用了大对象才会抛出OOM
     */
    public void setStackLength(){
        stackLength++;
        setStackLength();
    }

    public static void main(String[] args){
        JvmStackOOM jvmStackOOM = new JvmStackOOM();
        try{
           jvmStackOOM.setStackLength();
        }catch (Exception e){
            System.out.println("stackLength = "+jvmStackOOM.stackLength);
            e.printStackTrace();
        }
    }

}
