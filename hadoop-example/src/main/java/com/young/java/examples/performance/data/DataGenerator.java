package com.young.java.examples.performance.data;

import java.util.Properties;

/**
 * Created by dell on 2016/9/28.
 */
public interface DataGenerator {
    void generate(Properties config,String dist) throws Exception;
}
