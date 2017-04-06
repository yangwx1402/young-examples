package com.young.java.examples.configcenter;

/**
 * Created by dell on 2016/7/11.
 */
public class ConfigCenterClientBoot {
    public static void main(String[] args) throws InterruptedException {
        String zkServer = "10.16.124.30:2181";
        int timeout = 5000;
        ConfigClient<ConfigBean> client = new ConfigClient<>(zkServer, timeout,"/config");
        for(int i=0;i<15;i++){
            System.out.println(client.getConfig().getPort());
            Thread.sleep(1000);
        }
    }
}
