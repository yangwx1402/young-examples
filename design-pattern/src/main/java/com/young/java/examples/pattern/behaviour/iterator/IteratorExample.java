package com.young.java.examples.pattern.behaviour.iterator;

/**
 * Created by yangyong3 on 2017/7/3.
 */
public class IteratorExample {
    public static void main(String[] args) {
        Collection<Integer> collection = new ArrayCollection<>();
        for (int i = 0; i < 9; i++) {
            collection.add(i);
        }
        System.out.println(collection.get(5));
        System.out.println(collection.size());
        Iterator<Integer> it = collection.iterator();
        while(it.hasNext()){
            System.out.println(it.next());
        }
    }
}
