package com.young.java.examples.guava.collections.colllection;

/**
 * Created by yangyong3 on 2017/8/17.
 * SortedMultiset是Multiset 接口的变种，它支持高效地获取指定范围的子集。比方说，你可以用 latencies.subMultiset(0,BoundType.CLOSED, 100, BoundType.OPEN).size()来统计你的站点中延迟在100毫秒以内的访问，然后把这个值和latencies.size()相比，以获取这个延迟水平在总体访问中的比例。
 * <p/>
 * TreeMultiset实现SortedMultiset接口。在撰写本文档时，ImmutableSortedMultiset还在测试和GWT的兼容性。
 *
 */
public class SortedMultisetExample {
    
}
