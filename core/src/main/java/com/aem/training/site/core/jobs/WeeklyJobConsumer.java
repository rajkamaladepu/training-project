package com.aem.training.site.core.jobs;

import java.util.Set;

import org.apache.sling.event.jobs.Job;
import org.apache.sling.event.jobs.consumer.JobConsumer;
import org.apache.sling.settings.SlingSettingsService;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component(
        service = JobConsumer.class,
        property = {
                JobConsumer.PROPERTY_TOPICS + "=com/training/core/jobs/doajob"
        },
        // One of the few cases where immediate = true; this is so the Event Listener starts listening immediately
        immediate = true
)
public class WeeklyJobConsumer implements JobConsumer {
    private static final Logger LOG = LoggerFactory.getLogger(WeeklyJobConsumer.class);


    @Reference
    SlingSettingsService slingSettingsService;

    @Override
    public JobResult process(final Job job) {

        long startTime = System.currentTimeMillis();

        Set<String> runMode= slingSettingsService.getRunModes();

        if(!runMode.isEmpty() && (runMode.contains("dev"))) {
        	//do something
            LOG.info("Content Update Time Taken = {} seconds", ((System.currentTimeMillis() - startTime)/1000));
        }

        /**
         * Return the proper JobResult based on the work done...
         *
         * > OK : Processed successfully
         * > FAILED: Processed unsuccessfully and reschedule
         * > CANCEL: Processed unsuccessfully and do NOT reschedule
         * > ASYNC: Process through the JobConsumer.AsyncHandler interface
         */
        return JobResult.OK;
    }

}