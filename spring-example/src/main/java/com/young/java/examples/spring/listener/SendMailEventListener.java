package com.young.java.examples.spring.listener;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * Created by dell on 2016/8/18.
 */
@Component
public class SendMailEventListener implements ApplicationListener<SendMailEvent> {
    @Override
    public void onApplicationEvent(SendMailEvent sendMailEvent) {
        System.out.println("receive a SendMailEvent "+sendMailEvent.getMailAddress());
    }
}
