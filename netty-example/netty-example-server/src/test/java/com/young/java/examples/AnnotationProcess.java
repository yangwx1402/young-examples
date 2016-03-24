package com.young.java.examples;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/3/24.
 */
public class AnnotationProcess {
    public void process(Class<? extends Android> clazz) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        Annotation[] annotations = clazz.getDeclaredAnnotations();
        System.out.println(annotations.length);
        for(Annotation a:annotations){
            System.out.println(a.toString());
            Activity activity = (Activity) Class.forName(a.toString()).newInstance();
            activity.execute();
        }
    }
    public static void main(String[] args) throws IllegalAccessException, InstantiationException, ClassNotFoundException {
        AnnotationProcess process = new AnnotationProcess();
        process.process(AndroidDevelop.class);
        List<String> list = new ArrayList<String>();
        list.add("sdfsdf");
    }
}
