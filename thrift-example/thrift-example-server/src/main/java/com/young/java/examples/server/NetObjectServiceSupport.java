package com.young.java.examples.server;

import com.young.java.examples.api.NetObject;
import com.young.java.examples.api.NetObjectService;
import com.young.java.examples.api.NetResult;
import org.apache.commons.io.IOUtils;
import org.apache.thrift.TException;

import java.io.IOException;
import java.util.Hashtable;
import java.util.Map;

/**
 * Created by issuser on 2016/4/20.
 */
public class NetObjectServiceSupport implements NetObjectService.Iface {

    private Map<String,NetObject> cache = new Hashtable<>();

    @Override
    public NetResult put(NetObject obj) throws TException {
        System.out.println("id="+obj.getId()+","+obj.getType());
        try {
            System.out.println("stream="+ IOUtils.toString(obj.getStream(),"UTF-8"));
        } catch (IOException e) {
            e.printStackTrace();
            throw new TException("服务报错",e);
        }
        cache.put(obj.getId(),obj);
        NetResult result = new NetResult();
        result.setCode(200);
        result.setMessage("success");
        return result;
    }

    @Override
    public NetResult get(String objId) throws TException {
        NetResult result = new NetResult();
        result.setCode(200);
        result.setMessage("success");
        result.setObj(cache.get(objId));
        return result;
    }

    @Override
    public NetResult dele(String objId) throws TException {
        NetResult result = new NetResult();
        result.setCode(200);
        result.setMessage("success");
        cache.remove(objId);
        return result;
    }
}
