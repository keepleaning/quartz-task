package com.wanfang.quartz.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.wanfang.quartz.bean.ScheduleJob;
import com.wanfang.quartz.spring.SpringUtils;

/**
 * 
 * 说明：
 * 类名称：TaskUtils
 * 创建人： zhangsh 日期：2016年8月23日
 * 修改人： 日期：
 */
public class TaskUtils {
    public final static Logger log = Logger.getLogger(TaskUtils.class);

    /**
     * 通过反射调用scheduleJob中定义的方法
     * 
     * @param scheduleJob
     */
    public static void invokMethod(ScheduleJob scheduleJob) {
        Object object = null;
        Class<?> clazz = null;
        // springId不为空先按springId查找bean
        if (StringUtils.isNotBlank(scheduleJob.getSpringId())) {
            object = SpringUtils.getBean(scheduleJob.getSpringId());
        } else if (StringUtils.isNotBlank(scheduleJob.getBeanClass())) {
            try {
                clazz = Class.forName(scheduleJob.getBeanClass());
                object = clazz.newInstance();
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        }
        if (object == null) {
            log.error("任务名称 = [" + scheduleJob.getJobName() + "]---------------未启动成功，请检查是否配置正确！！！");
            return;
        }
        clazz = object.getClass();
        Method method = null;
        try {
            if (scheduleJob.getParam() != null) {
                method = clazz.getDeclaredMethod(scheduleJob.getMethodName(), scheduleJob.getParam().getClass());
            } else {
                method = clazz.getDeclaredMethod(scheduleJob.getMethodName());
            }
        } catch (NoSuchMethodException e) {
            log.error("任务名称 = [" + scheduleJob.getJobName() + "]---------------未启动成功，方法名设置错误！！！");
        } catch (SecurityException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        if (method != null) {
            try {
                if (scheduleJob.getParam() != null) {
                    method.invoke(object, scheduleJob.getParam());
                } else {
                    method.invoke(object);
                }
            } catch (IllegalAccessException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IllegalArgumentException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        System.out.println("任务名称 = [" + scheduleJob.getJobName() + "]----------启动成功");
    }
}
