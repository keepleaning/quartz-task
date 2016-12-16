package com.wanfang.quartz.util;

import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.wanfang.quartz.bean.ScheduleJob;

/**
 * 
 * 说明：计划任务执行处 无状态
 * 类名称：QuartzJobFactory
 * 创建人： zhangsh 日期：2016年8月23日
 * 修改人： 日期：
 */
public class QuartzJobFactory implements Job {
    public final Logger log = Logger.getLogger(this.getClass());

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        ScheduleJob scheduleJob = (ScheduleJob) context.getMergedJobDataMap().get("scheduleJob");
        TaskUtils.invokMethod(scheduleJob);
    }
}