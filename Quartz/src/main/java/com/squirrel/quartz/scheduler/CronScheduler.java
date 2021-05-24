package com.squirrel.quartz.scheduler;

import com.squirrel.quartz.job.CronJob;
import com.squirrel.quartz.job.CronJob2;
import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.TriggerBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Component;

@Component
public class CronScheduler {

    @Autowired
    private SchedulerFactoryBean schedulerFactoryBean;

    public void scheduleJobs() throws SchedulerException {
        Scheduler scheduler = schedulerFactoryBean.getScheduler();
        cronScheduler1(scheduler);
        cronScheduler2(scheduler);
    }

    private void cronScheduler1(Scheduler scheduler) throws SchedulerException {
        JobDetail jobDetail1 =
                JobBuilder.newJob(CronJob.class).withIdentity("job1", "group1").build();
        CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule("0/6 * * * * ?");
        CronTrigger cronTrigger =
                TriggerBuilder.newTrigger().withIdentity("trigger1", "group1")
                        .forJob(jobDetail1).withSchedule(scheduleBuilder).build();
        scheduler.scheduleJob(jobDetail1, cronTrigger);
    }

    private void cronScheduler2(Scheduler scheduler) throws SchedulerException {
        JobDetail jobDetail2 =
                JobBuilder.newJob(CronJob2.class).withIdentity("job2", "group2").build();
        CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule("0/12 * * * * ?");
        CronTrigger cronTrigger =
                TriggerBuilder.newTrigger().withIdentity("trigger2", "group2")
                        .forJob(jobDetail2).withSchedule(scheduleBuilder).build();
        scheduler.scheduleJob(jobDetail2, cronTrigger);
    }
}
