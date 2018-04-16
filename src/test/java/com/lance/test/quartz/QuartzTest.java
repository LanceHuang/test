package com.lance.test.quartz;

import org.junit.Test;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

/**
 * @author Lance
 * @date 2018/4/13 10:36
 */
public class QuartzTest {

    @Test
    public void test() throws Exception {
        JobDetail job = JobBuilder.newJob(HelloJob.class)
                .withIdentity("name1", "group1")
                .build();
        Trigger trigger = TriggerBuilder.newTrigger()
                .withIdentity("tigger1", "group1")
                .withSchedule(SimpleScheduleBuilder.repeatSecondlyForever())
                .build();

        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
        scheduler.scheduleJob(job, trigger);
        scheduler.start();
        Thread.sleep(10000);
        scheduler.shutdown();
    }
}


