package com.young.java.examples.jvm.instrumentation.statical;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;

/**
 * Created by yangyong on 17-3-25.
 * 实现一个类的转换器,该类作用是在类载入jvm之前可以进行转换,可以实现aop的功能(也就是说用新的类文件替换旧的类文件)
 */
public class PeopleClassFileTrasformer implements ClassFileTransformer {
    @Override
    public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined, ProtectionDomain protectionDomain, byte[] classfileBuffer) throws IllegalClassFormatException {
        System.out.println("load class "+className);
        if(className.endsWith("People")){
            try{
                CtClass ctClass = ClassPool.getDefault().get(className.replaceAll("/","."));
                CtMethod ctMethod = ctClass.getDeclaredMethod("sayHello");
                ctMethod.insertBefore("System.out.println(\"before say hello\");");
                ctMethod.insertAfter("System.out.println(\"after say hello\");");
                return ctClass.toBytecode();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return new byte[0];
    }
}
