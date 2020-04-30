package com.reedelk.scheduler.internal.scheduler;

import com.reedelk.runtime.api.component.InboundEventListener;
import com.reedelk.runtime.api.message.Message;
import com.reedelk.runtime.api.message.MessageAttributes;
import com.reedelk.runtime.api.message.MessageBuilder;
import com.reedelk.scheduler.component.Scheduler;
import com.reedelk.scheduler.internal.attribute.SchedulerAttributes;
import org.quartz.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.reedelk.scheduler.internal.scheduler.Messages.Scheduler.ERROR_QUARTZ_CONTEXT;

public class ExecuteFlowJob implements Job {

    private static final Logger logger = LoggerFactory.getLogger(ExecuteFlowJob.class);

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        MessageAttributes attributes = new SchedulerAttributes(jobExecutionContext);

        Message emptyMessage = MessageBuilder.get(Scheduler.class)
                .attributes(attributes)
                .empty()
                .build();


        SchedulerContext context;
        try {
            context = jobExecutionContext.getScheduler().getContext();
        } catch (SchedulerException exception) {
            String message = ERROR_QUARTZ_CONTEXT.format(exception.getMessage());
            logger.error(message, exception);
            throw new JobExecutionException(exception);
        }

        InboundEventListener inbound = (InboundEventListener) context.get(jobExecutionContext.getJobDetail().getKey().toString());
        inbound.onEvent(emptyMessage);
    }
}
