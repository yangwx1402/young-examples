package com.young.java.examples.shiro.role;

import com.young.java.examples.shiro.ShiroUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;

/**
 * Created by Administrator on 2016/5/3.
 */
public class UserRoleExample {

    public void checkRole(){
        Subject subject = ShiroUtils.getCurrentSubject("classpath:shiro-role.ini");
        UsernamePasswordToken token = new UsernamePasswordToken("zhang", "123");
        subject.login(token);
        System.out.println(subject.hasRole("role1"));
        System.out.println(subject.hasRole("role3"));
        System.out.println(subject.isPermitted("user:update"));
        System.out.println(subject.isPermitted("user:delete"));
        System.out.println(subject.isPermitted("user:select"));
    }
    public static void main(String[] args){
        UserRoleExample example = new UserRoleExample();
        example.checkRole();
    }
}
