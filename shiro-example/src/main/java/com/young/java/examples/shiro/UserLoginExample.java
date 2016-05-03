package com.young.java.examples.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;

/**
 * Created by Administrator on 2016/5/2.
 */
public class UserLoginExample {
    public void login() {
        //1.获取SecurityManager工厂,此处使用Ini配置文件初始化SecurityManager
        //Factory<org.apache.shiro.mgt.SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro.ini");
        Factory<org.apache.shiro.mgt.SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro-realm.ini");
        //2.得到SecurityManager,并绑定给SecurityUtils
        SecurityManager securityManager = factory.getInstance();
        SecurityUtils.setSecurityManager(securityManager);
        Subject subject = SecurityUtils.getSubject();
        //3.得到Subject及创建用户名和密码的身份验证Token
        UsernamePasswordToken token = new UsernamePasswordToken("zhang", "123");
        try {
            //4.登录
            subject.login(token);
        } catch (AuthenticationException e) {
            //登录失败
            e.printStackTrace();
        }
       // Assert.assertEquals(true, subject.isAuthenticated());
        //5.退出
        subject.logout();
    }
    public static void main(String[] args){
        UserLoginExample example = new UserLoginExample();
        example.login();
    }
}
