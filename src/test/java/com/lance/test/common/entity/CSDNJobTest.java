package com.lance.test.common.entity;

import org.junit.Test;

import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.Trigger;
import org.quartz.impl.StdSchedulerFactory;

import static org.quartz.CronScheduleBuilder.*;
import static org.quartz.JobBuilder.*;
import static org.quartz.TriggerBuilder.*;
import static org.quartz.DateBuilder.*;

/**
 * @author Lance
 * @since 2017/1/19
 */
public class CSDNJobTest {

    @Test
    public void execute() throws Exception {
        //创建调度器
        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();

        //启动调度器
        scheduler.start();

        //创建任务
//        JobDetail jobDetail = new JobDetailImpl("哈哈", Scheduler.DEFAULT_GROUP, CSDNJob.class);
        JobDetail jobDetail = newJob(CSDNJob.class)
                .withIdentity("哈哈")
                .build();

        //设置触发器
//        Trigger trigger = new CronTriggerImpl("呵呵", Scheduler.DEFAULT_GROUP, "0 0/1 * * * ?");
        Trigger trigger = newTrigger()
                .withIdentity("呵呵")
                .withSchedule(cronSchedule("0/1 * * * * ?"))
                .endAt(futureDate(10, IntervalUnit.SECOND))
                .build();

        //提交任务至调度器
        scheduler.scheduleJob(jobDetail, trigger);

        //任务终止
        Thread.sleep(10000);
        scheduler.shutdown(true);

        System.out.println("Timeout and finished...");
    }

}