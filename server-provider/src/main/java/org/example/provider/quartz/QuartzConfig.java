package org.example.provider.quartz;

import org.quartz.*;
import org.springframework.context.annotation.Configuration;

@Configuration
public class QuartzConfig {

//    @Bean
    public JobDetail job1() {
        return JobBuilder
                .newJob(PrintJob.class)
                .withIdentity("任务1", "定时任务组1")
                .storeDurably()  // 采用Bean配置的方式则必须调用此方法，否则会报错
                .build();
    }

//    @Bean
    public Trigger trigger1(JobDetail job1) {
        CronScheduleBuilder cronScheduleBuilder =
                CronScheduleBuilder
                        .cronSchedule("40/3 * * ? * * *")
                        .withMisfireHandlingInstructionDoNothing()
                ;
        return TriggerBuilder
                .newTrigger()
                .startNow()
                .forJob(job1)
                .withIdentity("触发器1", "定时任务组1")
                .withSchedule(cronScheduleBuilder)
                .build();
    }
}
