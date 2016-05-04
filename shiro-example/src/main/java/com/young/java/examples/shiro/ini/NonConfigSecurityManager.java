package com.young.java.examples.shiro.ini;

import com.young.java.examples.shiro.realm.UserAuthRealm;
import com.young.java.examples.shiro.role.UserRoleRealm;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.pam.AtLeastOneSuccessfulStrategy;
import org.apache.shiro.authc.pam.ModularRealmAuthenticator;
import org.apache.shiro.authz.ModularRealmAuthorizer;
import org.apache.shiro.authz.permission.WildcardPermissionResolver;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.subject.Subject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/5/4.
 */
public class NonConfigSecurityManager {
    public org.apache.shiro.mgt.DefaultSecurityManager createSecurityManagerByObject() {
        //securityManager应该设置成一个全局的变量
        org.apache.shiro.mgt.DefaultSecurityManager securityManager = new DefaultSecurityManager();
        //创建并设置authenticator
        ModularRealmAuthenticator authenticator = new ModularRealmAuthenticator();
        authenticator.setAuthenticationStrategy(new AtLeastOneSuccessfulStrategy());
        securityManager.setAuthenticator(authenticator);
        //设置authorizer
        ModularRealmAuthorizer authorizer = new ModularRealmAuthorizer();
        authorizer.setPermissionResolver(new WildcardPermissionResolver());
        securityManager.setAuthorizer(authorizer);

        List<Realm> lists = new ArrayList<Realm>();
        lists.add(new UserAuthRealm());
        lists.add(new UserRoleRealm());
        securityManager.setRealms(lists);
        SecurityUtils.setSecurityManager(securityManager);

        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken("zhang","123");
        subject.login(token);

        //角色role1是包含的
        System.out.println(subject.hasRole("role1"));
        //资源适配的是zhang:*,所有role1:sdr不适配,而下面适配
        System.out.println(subject.isPermitted("role1:sdr"));
        System.out.println(subject.isPermitted("zhang:sdr"));
        return securityManager;
    }

    public static void main(String[] args){
        NonConfigSecurityManager test = new NonConfigSecurityManager();
        test.createSecurityManagerByObject();
    }
}
