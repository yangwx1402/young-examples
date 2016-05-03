package com.young.java.examples.shiro.realm;

import org.apache.shiro.authc.*;
import org.apache.shiro.realm.Realm;

/**
 * Created by Administrator on 2016/5/2.
 */
public class UserAuthRealm implements Realm {
    @Override
    public String getName() {
        return "userAuth";
    }

    @Override
    public boolean supports(AuthenticationToken token) {
        //可以设置只支持UsernamePasswordToken
        return token instanceof UsernamePasswordToken;
    }

    @Override
    public AuthenticationInfo getAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String username = (String) token.getPrincipal();
        String password = new String((char[])token.getCredentials());
        if(!"zhang".equals(username)){
            throw new UnknownAccountException();
        }
        if(!"123".equals(password)){
            throw new IncorrectCredentialsException(); //如果密码错误
        }
        return new SimpleAuthenticationInfo(username,password,getName());
    }
}
