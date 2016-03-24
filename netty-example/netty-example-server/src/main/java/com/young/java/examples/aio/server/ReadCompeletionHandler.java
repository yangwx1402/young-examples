package com.young.java.examples.aio.server;

import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.util.Date;

/**
 * Created by issuser on 2016/3/22.
 */
public class ReadCompeletionHandler implements CompletionHandler<Integer, ByteBuffer> {

    private AsynchronousSocketChannel channel;

    public ReadCompeletionHandler(AsynchronousSocketChannel channel) {
        this.channel = channel;
    }

    public void completed(Integer result, ByteBuffer attachment) {
        attachment.flip();
        byte[] body = new byte[attachment.remaining()];
        attachment.get(body);
        try {
            String req = new String(body, "UTF-8");
            System.out.println("the server receive messagee " + req);
            doWrite(new Date().toString());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void doWrite(String message) {
        if (message != null && message.length() > 0) {
            byte[] bytes = message.getBytes();
            final ByteBuffer writeBuffer = ByteBuffer.allocate(bytes.length);
            writeBuffer.put(bytes);
            writeBuffer.flip();
            channel.write(writeBuffer, writeBuffer, new CompletionHandler<Integer, ByteBuffer>() {
                public void completed(Integer result, ByteBuffer attachment) {
                    if (attachment.hasRemaining())
                        channel.write(attachment, attachment, this);
                }

                public void failed(Throwable exc, ByteBuffer attachment) {
                    try {
                        channel.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }

    public void failed(Throwable exc, ByteBuffer attachment) {
        try {
            this.channel.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
