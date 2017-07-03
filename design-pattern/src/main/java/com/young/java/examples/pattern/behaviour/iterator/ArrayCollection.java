package com.young.java.examples.pattern.behaviour.iterator;

/**
 * Created by yangyong3 on 2017/7/3.
 * 迭代器模式，简单写了一个Collection，没有考虑边界情况，只是想说明下迭代器的实现方式。
 * 一般迭代器都出现在集合等场景中。
 */
public class ArrayCollection<T> implements Collection<T> {

    private Object[] array;

    private int index = 0;

    public ArrayCollection() {
        array = new Object[10];
    }

    @Override
    public void add(T t) {
        array[index] = t;
        index++;
    }

    @Override
    public T get(int index) {
        return (T) array[index];
    }

    @Override
    public Iterator<T> iterator() {
        return new ArrayIterator<T>();
    }

    @Override
    public int size() {
        return index;
    }

    public class ArrayIterator<T> implements Iterator<T> {

        private int sub_index = 0;

        @Override
        public T previous() {
            if (sub_index == 0) {
                sub_index = 0;
            } else {
                sub_index--;
            }
            return (T) array[sub_index];
        }

        @Override
        public T next() {
            return (T) array[sub_index++];
        }

        @Override
        public T first() {
            return (T) array[0];
        }

        @Override
        public boolean hasNext() {
            return sub_index != index;
        }
    }
}
