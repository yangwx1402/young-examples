package com.young.java.examples.spring.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

/**
 * Created by dell on 2016/8/18.
 */
@Service
public class BookingService {

    @Autowired
    private ApplicationContext applicationContext;

    public void booking(String roomId,String email){
        System.out.println(email+" booking a room room id is "+roomId);
        applicationContext.publishEvent(new SendMailEvent(this,email));
    }
}
