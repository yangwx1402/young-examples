package com.young.spring.rest.scheduler;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * Created by yangyong3 on 2017/2/4.
 * spring boot 定时任务
 */
@Component
public class UserScheduler {

    @Scheduled(fixedRate = 5000)
    //@Scheduled(cron = "")
    public void sendEmail(){
        System.out.println("每5秒钟发送邮件通知是否有新增用户");
    }
}
