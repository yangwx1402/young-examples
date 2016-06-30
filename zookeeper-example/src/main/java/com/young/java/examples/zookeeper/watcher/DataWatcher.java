package com.young.java.examples.zookeeper.watcher;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;

/**
 * Created by dell on 2016/6/30.
 */
public class DataWatcher implements Watcher {
    @Override
    public void process(WatchedEvent watchedEvent) {
        System.out.println("receive data  a event [type=" + watchedEvent.getType() + "],[state=" + watchedEvent.getState() + "],[path=" + watchedEvent.getPath() + "]");
    }
}
