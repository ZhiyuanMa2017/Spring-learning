package com.squirrel.quartz.scheduler;

import com.squirrel.quartz.job.Job;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Scheduler {

    @Bean
    public JobDetail jobDetail() {
        return JobBuilder
                .newJob(Job.class)
                .withIdentity("Job")
                .usingJobData("name", "World")
                .storeDurably()
                .build();
    }

    @Bean
    public Trigger trigger() {
        SimpleScheduleBuilder simpleScheduleBuilder =
                SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(2).repeatForever();
        return TriggerBuilder
                .newTrigger()
                .forJob(jobDetail())
                .withIdentity("trigger")
                .withSchedule(simpleScheduleBuilder)
                .build();
    }
}
