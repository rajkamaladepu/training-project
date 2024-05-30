package com.aem.training.site.core.jobs;

import org.apache.sling.event.jobs.JobBuilder;
import org.apache.sling.event.jobs.JobManager;
import org.apache.sling.event.jobs.ScheduledJobInfo;
import org.osgi.service.component.annotations.*;
import org.osgi.service.metatype.annotations.Designate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;

@Component(immediate = true, configurationPolicy=ConfigurationPolicy.REQUIRE)
@Designate(ocd = WeeklySchedulerConfiguration.class)
public class WeeklyJobScheduler {
    private static final Logger LOGGER = LoggerFactory.getLogger(WeeklyJobScheduler.class);

    @Reference
    private JobManager jobManager;

    @Activate
    protected void activate(WeeklySchedulerConfiguration config) {
        addScheduler(config);
    }

    @Modified
    protected void modified(WeeklySchedulerConfiguration config) {
        addScheduler(config);
    }

    /**
     * 
     * @param config
     */
    private void removeScheduler(WeeklySchedulerConfiguration config) {
        LOGGER.info("Weekly scheduler removal process started");
        Collection<ScheduledJobInfo> jobInfo = jobManager.getScheduledJobs();
        for(ScheduledJobInfo info : jobInfo){
            if(info.getJobTopic().equals("com/training/core/jobs/doajob")){
                info.unschedule();
                break;
            }
        }
    }

    @Deactivate
    protected void deactivate(WeeklySchedulerConfiguration config) {
        LOGGER.info("Weekly Scheduler has been deactivated");
        removeScheduler(config);
    }

    /**
     * This method adds the scheduler
     *
     * @param config
     */
    private void addScheduler(WeeklySchedulerConfiguration config) {
        if(config.enabled()) {
            removeScheduler(config);
            JobBuilder.ScheduleBuilder scheduleBuilder = jobManager.createJob("com/training/core/jobs/doajob")
                    .schedule()
                    .cron(config.cronExpression());

            if (scheduleBuilder.add() == null) {
                LOGGER.debug("Failed to add the JOBS");
            }

            LOGGER.info("Weekly Sling JOB added");
        } else {
            LOGGER.info("Weekly Sling JOB disabled");
            removeScheduler(config);
        }
    }

}