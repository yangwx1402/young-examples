package com.young.java.examples.nio.server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Date;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by Administrator on 2016/3/12.
 */
public class NioMultiplexerTimeServer implements Runnable {

    private Selector selector;

    private ServerSocketChannel serverSocketChannel;

    private volatile boolean stop;

    public NioMultiplexerTimeServer(int port) {
        //资源初始化
        try {
            //打开选择器,创建多路选择器
            selector = Selector.open();
            //打开ServerSocketChannel
            serverSocketChannel = ServerSocketChannel.open();
            //设置为非阻塞方式
            serverSocketChannel.configureBlocking(false);
            //进行端口绑定,backlog参数含义是最大pending的连接数
            serverSocketChannel.bind(new InetSocketAddress(port), 1024);
            //将serverSocketChannel注册到selector,监听ACCEPT事件
            SelectionKey key = serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
            System.out.println("server bind localhost port " + port + ", select key is " + key.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void stop() {
        this.stop = true;
    }

    public void run() {
        while (!stop) {
            try {
                //下面这个方法是会阻塞的,至少有一个Channel准备好以后才会返回,如果不想一直阻塞 可以设置一个timeout
                //例如
                selector.select(1000);
                //selector.select();
                System.out.println("不信来测试一下,在没有客户端连接上来之前啥都不会干");
                //获取已经就绪的SelectionKey
                Set<SelectionKey> selectionKeySet = selector.selectedKeys();
                System.out.println("selectionKeySet");
                Iterator<SelectionKey> it = selectionKeySet.iterator();
                SelectionKey key = null;
                while (it.hasNext()) {
                    key = it.next();
                    //删除从集合中返回的最后一个元素
                    it.remove();
                    try {
                        handInput(key);
                    } catch (Exception e) {
                        //如果处理中出现异常,那么也要正常的关闭Channel和SelectionKey
                        if (key != null) {
                            key.cancel();
                            if (key.channel() != null) {
                                key.channel().close();
                            }
                        }
                        e.printStackTrace();
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 处理客户端发送来的数据
     *
     * @param key
     * @throws IOException
     */
    private void handInput(SelectionKey key) throws IOException {
        if (key.isValid()) {
            //判断是否准备好来接收客户端连接
            if (key.isAcceptable()) {
                //返回为这个key创建的Channel
                ServerSocketChannel ssc = (ServerSocketChannel) key.channel();
                //让服务端做好准备 等待客户端连接,如果是Non-Blocking的会立刻返回null,否则会阻塞等待客户端连接
                SocketChannel sc = ssc.accept();
                if (sc != null) {
                    sc.configureBlocking(false);
                    sc.register(selector, SelectionKey.OP_READ);
                }
            }
            if (key.isReadable()) {
                SocketChannel sc = (SocketChannel) key.channel();
                ByteBuffer readBuffer = ByteBuffer.allocate(1024);
                if (sc.isOpen()) {
                    int readBytes = sc.read(readBuffer);
                    if (readBytes > 0) {
                        readBuffer.flip();
                        byte[] bytes = new byte[readBuffer.remaining()];
                        readBuffer.get(bytes);
                        String body = new String(bytes, "UTF-8");
                        System.out.println("The time server receive data " + body);
                        doWrite(sc, "the server time is " + new Date().toString());
                    } else if (readBytes < 0) {
                        key.channel();
                        sc.close();
                    } else
                        ;
                }
            }
        }
    }

    private void doWrite(SocketChannel sc, String response) throws IOException {
        if (response != null && response.trim().length() > 0) {
            byte[] bytes = response.getBytes();
            ByteBuffer writeBuffer = ByteBuffer.allocate(bytes.length);
            writeBuffer.put(bytes);
            writeBuffer.flip();
            sc.write(writeBuffer);
        }
    }

    public static void main(String[] args) {
        int port = 8080;
        new Thread(new NioMultiplexerTimeServer(port)).start();
    }
}
