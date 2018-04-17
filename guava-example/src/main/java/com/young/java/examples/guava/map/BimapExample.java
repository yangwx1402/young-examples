package com.young.java.examples.guava.map;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;

/**
 * BiMap是一种key value互换的Map
 * @author shazam
 * @DATE 2018/4/17
 */
public class BimapExample {

    public void map() {
        BiMap<String, Integer> map = HashBiMap.create();
        for (int i = 0; i < 100; i++) {
            map.put("index_" + i, i);
        }
        System.out.println(map);
        System.out.println(map.get("index_1"));
        BiMap<Integer,String> reverse = map.inverse();
        System.out.println(reverse);
        System.out.println(reverse.get(1));
    }
    public static void main(String[] args){
      BimapExample example = new BimapExample();
      example.map();
    }
}
