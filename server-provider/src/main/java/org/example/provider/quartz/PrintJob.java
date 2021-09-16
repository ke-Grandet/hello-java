package org.example.provider.quartz;

import lombok.extern.slf4j.Slf4j;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

@Slf4j
public class PrintJob extends QuartzJobBean {
    @Override
    public void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter
                .ofPattern("yyyy-MM-dd HH:mm:ss", Locale.CHINA);
        String time = LocalDateTime.now().format(dateTimeFormatter);

        log.info("整分钟报时，当前时间：{}", time);
    }
}
