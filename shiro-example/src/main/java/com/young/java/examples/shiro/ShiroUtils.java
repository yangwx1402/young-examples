package com.young.java.examples.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.*;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;

/**
 * Created by Administrator on 2016/5/3.
 */
public class ShiroUtils {

    public static Subject getCurrentSubject(String configFile){
        //1.获取SecurityManager工厂,此处使用Ini配置文件初始化SecurityManager
        //Factory<org.apache.shiro.mgt.SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro.ini");
        Factory<org.apache.shiro.mgt.SecurityManager> factory = new IniSecurityManagerFactory(configFile);
        //2.得到SecurityManager,并绑定给SecurityUtils
        SecurityManager securityManager = factory.getInstance();
        SecurityUtils.setSecurityManager(securityManager);
        return SecurityUtils.getSubject();
    }
}
