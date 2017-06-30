package com.young.java.examples.pattern.behaviour.templatemethod;

/**
 * Created by yangyong3 on 2017/6/30.
 */
public class JDloginAction extends AbstractLoginAction implements LoginAction {
    @Override
    public void openUrl(String url) {
        System.out.println("打开京东登录url");
    }

    @Override
    public String inputUserName() {
        System.out.println("输入用户名");
        return null;
    }

    @Override
    public String inputPassword() {
        System.out.println("输入密码");
        return null;
    }

    @Override
    public String submit() {
        System.out.println("点击提交登录成功，获取cookie");
        return null;
    }
}
