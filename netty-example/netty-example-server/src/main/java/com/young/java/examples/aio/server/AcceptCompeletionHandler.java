package com.young.java.examples.aio.server;

import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;

/**
 * Created by issuser on 2016/3/22.
 */
public class AcceptCompeletionHandler implements CompletionHandler<AsynchronousSocketChannel, AsyncTimeServerHandler> {
    public void completed(AsynchronousSocketChannel result, AsyncTimeServerHandler attachment) {
        attachment.getAsynchronousServerSocketChannel().accept(attachment, this);
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        //参数1.buffer为接收缓冲区,用于从异步的channel中读取数据包
        //参数2.attachment 为异步Channel携带的附件，通知回调的时候作为参数传入使用
        result.read(buffer, buffer, new ReadCompeletionHandler(result));
    }

    public void failed(Throwable exc, AsyncTimeServerHandler attachment) {
        exc.printStackTrace();
        attachment.getLatch().countDown();
    }
}
