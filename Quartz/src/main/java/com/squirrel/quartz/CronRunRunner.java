package com.squirrel.quartz;

import com.squirrel.quartz.scheduler.CronScheduler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class CronRunRunner implements CommandLineRunner {

    @Autowired
    private CronScheduler cronScheduler;

    @Override
    public void run(String... args) throws Exception {
        cronScheduler.scheduleJobs();
        System.out.println("Task running");
    }
}
