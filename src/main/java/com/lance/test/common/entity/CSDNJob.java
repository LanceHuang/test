package com.lance.test.common.entity;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * CSDN爬虫任务
 *
 * @author Lance
 * @since 2017/1/19
 */
public class CSDNJob implements Job {

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println("---------------------------------------------------------");
        System.out.println("Start job... " + sdf.format(new Date()));
        System.out.println("Execute job...");
        System.out.println("Finish job... " + sdf.format(new Date()));
    }
}
