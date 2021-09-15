package org.example.provider.quartz;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.text.SimpleDateFormat;
import java.util.Date;

public class PrintJob extends QuartzJobBean {
    @Override
    public void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        String time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        System.out.println("当前时间：" + time);
    }
}
