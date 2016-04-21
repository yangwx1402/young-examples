package com.young.java.examples.client;

import com.young.java.examples.api.NetField;
import com.young.java.examples.api.NetObject;
import com.young.java.examples.api.NetObjectService;
import com.young.java.examples.api.NetResult;
import org.apache.commons.io.IOUtils;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by issuser on 2016/4/20.
 */
public class NetObjectClient {

    private NetObjectService.Client client = null;

    private TTransport transport = null;

    public NetObjectClient() {
        transport  = new TSocket("localhost", 7911);
        try {
            transport.open();
            //使用高密度二进制协议
            TProtocol protocol = new TCompactProtocol(transport);
            //创建Client
            client = new NetObjectService.Client(protocol);
        } catch (TTransportException e) {
            e.printStackTrace();
        }
    }

    public void destory(){
        transport.close();
    }

    public void put() throws IOException, TException {
        NetObject obj = new NetObject();
        obj.setId("1");
        Map<String,NetField> map = new HashMap<String,NetField>();
        NetField field = new NetField();
        field.setName("size");
        field.setValue("100");
        map.put("size",field);
        obj.setFileds(map);
        obj.setType(1);
        obj.setStream(IOUtils.toByteArray(new FileInputStream("D:\\net-object.thrift")));
        client.put(obj);
    }

    public void get() throws TException, IOException {
        NetResult result = client.get("1");
        if(result.getObj()!=null){
            System.out.println(IOUtils.toString(result.getObj().getStream(),"UTF-8"));
        }
    }

    public void del() {

    }

    public static void main(String[] args) throws TException, IOException {
        NetObjectClient client = new NetObjectClient();
        client.put();
        client.get();
    }
}
