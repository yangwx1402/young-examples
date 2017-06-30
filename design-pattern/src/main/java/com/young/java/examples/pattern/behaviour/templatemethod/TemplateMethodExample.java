package com.young.java.examples.pattern.behaviour.templatemethod;

/**
 * Created by yangyong3 on 2017/6/30.
 */
public class TemplateMethodExample {
    public static void main(String[] args) {
        LoginAction login = new JDloginAction();
        login.login("jd");
    }
}
