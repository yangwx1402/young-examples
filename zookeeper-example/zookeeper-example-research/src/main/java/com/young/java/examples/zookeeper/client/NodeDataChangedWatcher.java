package com.young.java.examples.zookeeper.client;

import com.young.java.examples.zkclient.IZkDataListener;

/**
 * Created by dell on 2016/7/11.
 */
public class NodeDataChangedWatcher implements IZkDataListener {
    @Override
    public void handleDataChange(String dataPath, Object data) throws Exception {
        System.out.println(dataPath+" data changed new data is -"+data.toString());
    }

    @Override
    public void handleDataDeleted(String dataPath) throws Exception {
        System.out.println(dataPath+" data deleted");
    }
}
