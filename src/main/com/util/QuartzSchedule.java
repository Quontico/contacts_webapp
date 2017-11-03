package util;

import org.apache.log4j.Logger;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

public class QuartzSchedule {
    private static Logger LOGGER = Logger.getRootLogger();

    public static void quartzExecute() throws SchedulerException, InterruptedException {
        try {
            JobDetail job = JobBuilder.newJob(QuartzJob.class)
                    .withIdentity("QuartzJob").build();

            Trigger trigger = TriggerBuilder.newTrigger()
                    .withIdentity("CronTrigger")
                    .withSchedule(CronScheduleBuilder.dailyAtHourAndMinute(22, 34))
                    .build();

            Scheduler scheduler = new StdSchedulerFactory().getScheduler();
            scheduler.start();
            scheduler.scheduleJob(job, trigger);

        } catch (Exception e) {
            LOGGER.error("There is some problem with Quartz: " + e);
            e.printStackTrace();
        }
    }
}
