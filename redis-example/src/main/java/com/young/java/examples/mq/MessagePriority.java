package com.young.java.examples.mq;

import java.util.Comparator;

/**
 * Created by yangyong3 on 2016/12/21.
 */
public enum MessagePriority implements Comparator<MessagePriority> {
    high("high", 10), middle("middle", 5), low("low", 1);

    private String name;

    private Integer priority;

    MessagePriority(String name, Integer priority) {
        this.name = name;
        this.priority = priority;
    }

    String getName() {
        return name;
    }

    Integer getPriority() {
        return priority;
    }

    public int compare(MessagePriority o1, MessagePriority o2) {
        return o1.getPriority().compareTo(o2.getPriority());
    }
}
