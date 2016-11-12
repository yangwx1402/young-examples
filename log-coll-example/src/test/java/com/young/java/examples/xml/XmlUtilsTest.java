package com.young.java.examples.xml;

import com.young.java.examples.bean.*;
import com.young.java.examples.utils.XmlUtils;
import org.apache.commons.io.FileUtils;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

/**
 * Created by young.yang on 2016/11/12.
 */
public class XmlUtilsTest {
    @Test
    public void testFromXml() throws IOException {
        XmlUtils xmlUtils = new XmlUtils(LogConfigBean.class, LogModuleBean.class,LogOperBean.class, LogHandlerBean.class, LogArgBean.class);
        String file = "E:\\young\\java\\young-examples\\log-coll-example\\src\\main\\resources\\log.xml";
        String xml = FileUtils.readFileToString(new File(file),"utf-8");
        LogConfigBean logConfigBean = xmlUtils.fromXml(xml,LogConfigBean.class);
        System.out.println(logConfigBean.getModuler().get(0).getOpers());
    }

}
