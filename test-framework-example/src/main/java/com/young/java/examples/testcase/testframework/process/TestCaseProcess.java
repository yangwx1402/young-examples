package com.young.java.examples.testcase.testframework.process;


import com.young.java.examples.annotation.common.process.AnnotationProcess;
import com.young.java.examples.testcase.testframework.annotations.TestCase;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
/**
 * Created by dell on 2016/5/10.
 */
public class TestCaseProcess implements AnnotationProcess {

    private static Log log = LogFactory.getLog(TestCaseProcess.class);

    @Override
    public void process(Class clazz) throws Exception {
        Annotation annotation = clazz.getAnnotation(TestCase.class);
        Method method = annotation.getClass().getMethod("value");
        String name = (String) method.invoke(annotation);
        Object obj = clazz.newInstance();
        Method[] methods = clazz.getDeclaredMethods();
        if (methods != null && methods.length > 0) {
            long start = System.currentTimeMillis();
            log.info("begining test name = [" + name + "],class=[" + clazz.getName() + "]");
            for (Method m : methods) {
                long methodstart = System.currentTimeMillis();
                log.info("execute method name =[" + m.getName() + "],result is =[" + m.invoke(obj) + "],method cost time =[" + (System.currentTimeMillis() - methodstart) + "]");
            }
            log.info("end test " + name + ", all cost time =[" + (System.currentTimeMillis() - start) + "]");
        }
    }
}
