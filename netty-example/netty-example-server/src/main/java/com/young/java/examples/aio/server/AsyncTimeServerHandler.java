package com.young.java.examples.aio.server;

import java.net.InetSocketAddress;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.util.concurrent.CountDownLatch;

/**
 * Created by issuser on 2016/3/22.
 */
public class AsyncTimeServerHandler implements Runnable {

    private int port;

    private CountDownLatch latch;

    public CountDownLatch getLatch() {
        return latch;
    }

    private AsynchronousServerSocketChannel asynchronousServerSocketChannel;

    public AsynchronousServerSocketChannel getAsynchronousServerSocketChannel(){
        return asynchronousServerSocketChannel;
    }

    public AsyncTimeServerHandler(int port) {
        this.port = port;
        try {
            asynchronousServerSocketChannel = AsynchronousServerSocketChannel.open();
            asynchronousServerSocketChannel.bind(new InetSocketAddress(port));
            System.out.println("time server bind in port " + port);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void run() {
        latch = new CountDownLatch(1);
        doAccept();
        try {
            latch.await();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void doAccept() {
        asynchronousServerSocketChannel.accept(this, new AcceptCompeletionHandler());
    }

    public static void main(String[] args){
        AsyncTimeServerHandler serverHandler = new AsyncTimeServerHandler(9999);
        new Thread(serverHandler).start();
    }
}
