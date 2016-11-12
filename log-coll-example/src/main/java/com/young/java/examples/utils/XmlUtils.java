package com.young.java.examples.utils;

import com.thoughtworks.xstream.XStream;

/**
 * Created by young.yang on 2016/11/12.
 */
public class XmlUtils {

    private XStream xStream;

    public XmlUtils(Class... clazz){
        xStream = new XStream();
        xStream.autodetectAnnotations(true);
        xStream.processAnnotations(clazz);
    }

    public String toXml(Object object){
        return xStream.toXML(object);
    }

    public <T> T fromXml(String xml,Class<T> tClass){
        return (T) xStream.fromXML(xml);
    }

}
