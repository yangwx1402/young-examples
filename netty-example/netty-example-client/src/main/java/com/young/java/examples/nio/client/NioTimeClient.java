package com.young.java.examples.nio.client;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Date;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by Administrator on 2016/3/12.
 */
public class NioTimeClient implements Runnable {

    private String host;

    private int port;

    private Selector selector;

    private SocketChannel socketChannel;

    private volatile boolean stop;

    public NioTimeClient(String host, int port) {
        this.host = host;
        this.port = port;
        try {
            //初始化资源
            selector = Selector.open();
            socketChannel = SocketChannel.open();
            socketChannel.configureBlocking(false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void run() {
        try {
            //进行连接服务端
            doConnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
        //已经进行连接
        while (!stop) {
            try {
                selector.select(1000);
                Set<SelectionKey> selectionKeySet = selector.selectedKeys();
                Iterator<SelectionKey> it = selectionKeySet.iterator();
                SelectionKey key = null;
                while (it.hasNext()) {
                    key = it.next();
                    it.remove();
                    try {
                        handInput(key);
                    } catch (Exception e) {
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

    //往服务端写入数据
    private void handInput(SelectionKey key) throws IOException {
        //判断key是否有效,因为selector和channel关闭都会使key无效
        if (key.isValid()) {
            SocketChannel sc = (SocketChannel) key.channel();
            //也就是说Socket是可连接的
            if (key.isConnectable()) {
                //判断是否连接成功,因为有可能连接失败
                if (sc.finishConnect()) {
                    //成功以后向selector注册READ事件,关注数据读取事件,也就是说一旦服务器给客户端返回数据后可以进行读取
                    sc.register(selector, SelectionKey.OP_READ);
                    //向服务端写入数据
                    doWrite(sc);
                } else {
                    //连接失败
                    System.exit(1);
                }
            }
            //这里是处理从服务端读取回来的数据
            if (key.isReadable()) {
                ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                int readBytes = sc.read(byteBuffer);
                if (readBytes > 0) {
                    byteBuffer.flip();
                    byte[] bytes = new byte[byteBuffer.remaining()];
                    byteBuffer.get(bytes);
                    String body = new String(bytes, "UTF-8");
                    System.out.println("Now body is -"+body);
                    this.stop = true;
                }else if(readBytes<0){
                    key.cancel();
                    sc.close();
                }else
                    ;
            }
        }

    }

    //给服务端发送数据
    private void doWrite(SocketChannel sc) throws IOException {
        byte[] req = ("Client time is "+new Date().toString()).getBytes();
        ByteBuffer writeBuffer = ByteBuffer.allocate(req.length);
        writeBuffer.put(req);
        writeBuffer.flip();
        sc.write(writeBuffer);
        if(!writeBuffer.hasRemaining()){
            System.out.println("client write data to server success");
        }
    }

    /**
     * 连接服务端
     * @throws IOException
     */
    private void doConnect() throws IOException {
         if(socketChannel.connect(new InetSocketAddress(host,port))){
             socketChannel.register(selector,SelectionKey.OP_READ);
             doWrite(socketChannel);
         }else{
             socketChannel.register(selector,SelectionKey.OP_CONNECT);
         }
    }
    public static void main(String[] args){
       new Thread(new NioTimeClient("localhost",8080)).start();
    }
}
