package org.example.provider.service.impl;

import org.example.provider.service.IHelloService;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class HelloServiceImpl implements IHelloService {

    @Autowired
    Scheduler scheduler;

    @Override
    public void pause(String triggerName, String triggerGroup) {
        try {
            scheduler.pauseAll();
//            scheduler.pauseTrigger(new TriggerKey(triggerName, triggerGroup));
            System.out.println("暂停成功: " + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
                    .format(new Date()));
        } catch (SchedulerException e) {
            System.out.println("暂停失败: " + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
                    .format(new Date()));
            e.printStackTrace();
        }
    }

    @Override
    public void resume(String triggerName, String triggerGroup) {
        try {
            scheduler.resumeAll();
//            scheduler.resumeTrigger(new TriggerKey(triggerName, triggerGroup));
            System.out.println("恢复成功: " + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
                    .format(new Date()));
        } catch (SchedulerException e) {
            System.out.println("恢复失败: " + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
                    .format(new Date()));
            e.printStackTrace();
        }
    }
}
