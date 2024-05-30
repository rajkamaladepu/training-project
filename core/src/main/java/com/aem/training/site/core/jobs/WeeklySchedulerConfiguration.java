package com.aem.training.site.core.jobs;

import org.osgi.service.metatype.annotations.AttributeDefinition;
import org.osgi.service.metatype.annotations.AttributeType;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;

@ObjectClassDefinition(
        name = "Weekly Scheduled Job: SlingSchedulerConfiguration",
        description = "Sling scheduler configuration for set of weekly jobs"
)
public @interface WeeklySchedulerConfiguration {
 
    @AttributeDefinition(
            name = "Weekly Scheduler",
            description = "Weekly Scheduler to run re-occurring jobs ",
            type = AttributeType.STRING)
    public String schedulerName() default "Weekly Scheduler";
 
    @AttributeDefinition(
            name = "Enabled",
            description = "True, if scheduler service is enabled",
            type = AttributeType.BOOLEAN)
    public boolean enabled() default true;
 
    @AttributeDefinition(
            name = "Cron Expression",
            description = "Cron expression used by the scheduler",
            type = AttributeType.STRING)
    public String cronExpression() default "0 30 23 ? * SAT *"; // runs every Saturday at 2330 hours UTC

}