package com.young.java.examples.boot;

import com.young.java.examples.api.NetObjectService;
import com.young.java.examples.server.NetObjectServiceSupport;
import org.apache.thrift.TProcessor;
import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.protocol.TProtocolFactory;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TThreadPoolServer;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TServerTransport;

/**
 * Created by issuser on 2016/4/20.
 */
public class NetObjectServiceBoot {
    public static void main(String[] args){
        try {
            //设置传输通道，普通通道
            TServerTransport serverTransport = new TServerSocket(7911);

            //使用高密度二进制协议
            TProtocolFactory proFactory = new TCompactProtocol.Factory();

            //设置处理器HelloImpl
            TProcessor processor = new NetObjectService.Processor(new NetObjectServiceSupport());

            //创建服务器
            TServer server = new TThreadPoolServer(
                    new TThreadPoolServer.Args(serverTransport)
                            .protocolFactory(proFactory)
                            .processor(processor)
            );

            System.out.println("Start server on port 7911...");
            server.serve();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
