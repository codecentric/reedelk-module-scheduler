package de.codecentric.reedelk.scheduler.internal.scheduler;

import de.codecentric.reedelk.runtime.api.commons.StringUtils;
import de.codecentric.reedelk.runtime.api.component.InboundEventListener;
import de.codecentric.reedelk.scheduler.component.CronConfiguration;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;

import java.util.TimeZone;

import static org.quartz.CronScheduleBuilder.cronSchedule;

class SchedulingStrategySchedulerCron implements SchedulingStrategyScheduler {

    private final CronConfiguration configuration;

    SchedulingStrategySchedulerCron(CronConfiguration configuration) {
        this.configuration = configuration;
    }

    @Override
    public SchedulerJob schedule(InboundEventListener listener) {
        JobDetail job = JobBuilder.newJob(ExecuteFlowJob.class).build();

        String expression = configuration.getExpression();
        Trigger trigger = TriggerBuilder.newTrigger()
                .withSchedule(cronSchedule(expression).inTimeZone(getTimeZoneOrDefault()))
                .build();

        scheduleJob(listener, job, trigger);
        return new SchedulerJob(job.getKey());
    }

    void scheduleJob(InboundEventListener listener, JobDetail job, Trigger trigger) {
        SchedulerProvider.scheduler().scheduleJob(listener, job, trigger);
    }

    TimeZone getTimeZoneOrDefault() {
        String timeZone = configuration.getTimeZone();
        if (StringUtils.isBlank(timeZone) || CronConfiguration.SYSTEM_TIMEZONE.equals(timeZone)) {
            return TimeZone.getDefault();
        } else {
            return TimeZone.getTimeZone(timeZone);
        }
    }
}
