package de.codecentric.reedelk.scheduler.internal.scheduler;

import de.codecentric.reedelk.runtime.api.component.InboundEventListener;
import de.codecentric.reedelk.runtime.api.message.Message;
import de.codecentric.reedelk.runtime.api.message.MessageAttributes;
import de.codecentric.reedelk.runtime.api.message.MessageBuilder;
import de.codecentric.reedelk.scheduler.component.Scheduler;
import de.codecentric.reedelk.scheduler.internal.attribute.SchedulerAttributes;
import org.quartz.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
            String message = Messages.Scheduler.ERROR_QUARTZ_CONTEXT.format(exception.getMessage());
            logger.error(message, exception);
            throw new JobExecutionException(exception);
        }

        InboundEventListener inbound = (InboundEventListener) context.get(jobExecutionContext.getJobDetail().getKey().toString());
        inbound.onEvent(emptyMessage);
    }
}
