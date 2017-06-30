package com.young.java.examples.pattern.behaviour.templatemethod;

/**
 * Created by yangyong3 on 2017/6/30.
 * 模板方法模式，可以在抽象类中定义一系列模板方法，并且在抽象类中实现整个调用流程，
 * 在子类中实现具体的模板方法。
 */
public abstract class AbstractLoginAction implements LoginAction{

    public abstract void openUrl(String url);

    public abstract String inputUserName();

    public abstract String inputPassword();

    public abstract String submit();

    public String login(String loginUrl){
        openUrl(loginUrl);
        inputUserName();
        inputPassword();
        return submit();
    }
}
