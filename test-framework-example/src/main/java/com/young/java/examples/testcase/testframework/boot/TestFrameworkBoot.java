package com.young.java.examples.testcase.testframework.boot;


import com.young.java.examples.annotation.common.boot.AnnotationBoot;
import com.young.java.examples.annotation.common.process.AnnotationProcess;
import com.young.java.examples.testcase.testframework.config.TestConfig;
import com.young.java.examples.testcase.testframework.process.TestCaseProcess;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dell on 2016/5/10.
 */
public class TestFrameworkBoot {
    public static void main(String[] args) throws Exception {
        AnnotationBoot boot = new AnnotationBoot();
        List<AnnotationProcess> proccess = new ArrayList<AnnotationProcess>();
        proccess.add(new TestCaseProcess());
        boot.boot(new Class[]{TestConfig.class}, proccess);
    }
}
