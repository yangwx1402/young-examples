package com.young.java.examples.pattern.structure.flyweight;

/**
 * Created by yangyong3 on 2017/6/30.
 */
public class FlyWeightExample {
    public static void main(String[] args) throws Exception {
        ConnectionPool pool = new ConnectionPool();
       for(int i=0;i<100;i++){
           Connection con = pool.getConnection();
           System.out.println(con);
           pool.close(con);
       }
    }
}
