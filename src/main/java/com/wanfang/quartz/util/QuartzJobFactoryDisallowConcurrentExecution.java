package com.wanfang.quartz.util;

import org.apache.log4j.Logger;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.wanfang.quartz.bean.ScheduleJob;

/**
 * 
 * 说明：若一个方法一次执行不完下次轮转时则等待改方法执行完后才执行下一次操作
 * 类名称：QuartzJobFactoryDisallowConcurrentExecution
 * 创建人： zhangsh 日期：2016年8月23日
 * 修改人： 日期：
 */
@DisallowConcurrentExecution
public class QuartzJobFactoryDisallowConcurrentExecution implements Job {
    public final Logger log = Logger.getLogger(this.getClass());

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        ScheduleJob scheduleJob = (ScheduleJob) context.getMergedJobDataMap().get("scheduleJob");
        TaskUtils.invokMethod(scheduleJob);

    }
}