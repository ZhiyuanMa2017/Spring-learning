package com.squirrel.quartz;

import com.squirrel.quartz.scheduler.CronScheduler;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Configuration
@EnableScheduling
@Component
public class CronRunScheduled {

    @Autowired
    private CronScheduler cronScheduler;

    @Scheduled(cron = "0 30 11 25 11 ?")
    public void schedule() throws SchedulerException {
        cronScheduler.scheduleJobs();
    }
}
