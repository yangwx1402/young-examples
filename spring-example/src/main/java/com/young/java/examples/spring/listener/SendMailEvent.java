package com.young.java.examples.spring.listener;

import org.springframework.context.ApplicationEvent;

import java.io.Serializable;

/**
 * Created by dell on 2016/8/18.
 */
public class SendMailEvent extends ApplicationEvent implements Serializable {

    private String mailAddress;

    public SendMailEvent(Object source) {
        super(source);
    }

    public SendMailEvent(Object source, String mailAddress) {
        super(source);
        this.mailAddress = mailAddress;
    }

    public String getMailAddress() {
        return mailAddress;
    }
}
