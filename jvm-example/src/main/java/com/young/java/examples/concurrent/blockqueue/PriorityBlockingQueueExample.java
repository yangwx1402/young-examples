package com.young.java.examples.concurrent.blockqueue;

/**
 * Created by young.yang on 2016/11/21.
 */

import java.util.concurrent.PriorityBlockingQueue;

/**
 * PriorityBlockingQueue 类实现了 BlockingQueue 接口。
 PriorityBlockingQueue 是一个无界的并发队列。它使用了和类 java.util.PriorityQueue 一样的排序规则。你无法向这个队列中插入 null 值。
 所有插入到 PriorityBlockingQueue 的元素必须实现 java.lang.Comparable 接口。因此该队列中元素的排序就取决于你自己的 Comparable 实现。
 注意 PriorityBlockingQueue 对于具有相等优先级(compare() == 0)的元素并不强制任何特定行为。
 同时注意，如果你从一个 PriorityBlockingQueue 获得一个 Iterator 的话，该 Iterator 并不能保证它对元素的遍历是以优先级为序的。
 */
public class PriorityBlockingQueueExample {

    private static class PriorityElement implements Comparable<PriorityElement>{

        private Integer value;

        public PriorityElement(Integer value){
            this.value = value;
        }

        public String toString(){return value+"";}

        @Override
        public int compareTo(PriorityElement o) {
            return -value.compareTo(o.value);
        }
    }

    public static void main(String[] args){
        PriorityBlockingQueue<PriorityElement> queue = new PriorityBlockingQueue<>();
        queue.put(new PriorityElement(10));
        queue.put(new PriorityElement(5));
        queue.put(new PriorityElement(20));
        //這時候彈出來的第一個元素肯定是最大或者最小的元素,大小是有Comparable的實現決定的。
        System.out.println(queue.poll());
    }
}
