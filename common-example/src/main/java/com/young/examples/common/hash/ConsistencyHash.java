package com.young.examples.common.hash;

import java.util.SortedMap;
import java.util.TreeMap;

/**一致性hash的java实现,用来做存储分片,如数据库分库,缓存分片等等
 * Created by Administrator on 2016/5/21.
 */
public class ConsistencyHash<Shard> {

    private TreeMap<Long, Shard> treeMap = new TreeMap<>();

    private Hash hash;

    public ConsistencyHash(Hash hash) {
        this.hash = hash;
    }

    public void addShard(Shard realShard, int virtual_num) {
        for (int i = 0; i < virtual_num; i++) {
            treeMap.put(hash.hashing(realShard.toString() + "_" + i), realShard);
        }
    }


    public Shard getShard(String key) {
        Long keyHash = hash.hashing(key);
        SortedMap<Long, Shard> tail = treeMap.tailMap(keyHash);
        if (tail == null || tail.isEmpty()) {
            return treeMap.get(treeMap.firstKey());
        } else {
            return tail.get(tail.firstKey());
        }
    }

    public static void main(String[] args){
        ConsistencyHash<String> consistencyHash = new ConsistencyHash<>(new MD5Hash());
        for(int i=1;i<10;i++){
            consistencyHash.addShard("192.168.1."+i,100);
        }
        for(int i=0;i<100;i++){
            System.out.println(consistencyHash.getShard(i+""));
        }

    }

}
