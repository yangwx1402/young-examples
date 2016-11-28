package com.young.java.examples.example;

import com.young.java.examples.annotation.Log;
import org.springframework.stereotype.Component;

/**
 * Created by young.yang on 2016/11/12.
 */
@Component
public class DaoImpl implements Dao {
    @Override
    public void add(String name, String age) {
        System.out.println("DaoImpl add run");
    }

    @Override
    public void delete(int id) {
        System.out.println("DaoImpl delete run");
    }
}
