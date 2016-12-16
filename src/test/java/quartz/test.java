package quartz;

import org.junit.Before;
import org.junit.Test;
import org.quartz.SchedulerException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

public class test {
    SchedulerFactoryBean schedulerFactoryBean;

    @Before
    public void init() {
        // 加载spring配置文件
        ApplicationContext appcontext = new FileSystemXmlApplicationContext(
                new String[] { "classpath:spring.xml", "classpath:spring-mybatis.xml", "classpath:spring-redis.xml" });
        // 接口Service
        schedulerFactoryBean = appcontext.getBean(SchedulerFactoryBean.class);
    }

    @Test
    public void testGetAllTask() {
        /*
         * TaskSearchBean taskSearchBean = new TaskSearchBean();
         * taskSearchBean.setStart(0); taskSearchBean.setLength(10); ListInfo
         * listInfo = jobTaskService.getAllTask(taskSearchBean);
         * System.out.println(listInfo.getList().size());
         */
    }

    @Test
    public void testAddTask() throws SchedulerException {

    }
}
