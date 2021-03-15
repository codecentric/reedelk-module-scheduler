package de.codecentric.reedelk.scheduler.internal.attribute;

import de.codecentric.reedelk.runtime.api.annotation.Type;
import de.codecentric.reedelk.runtime.api.annotation.TypeProperty;
import de.codecentric.reedelk.runtime.api.message.MessageAttributes;
import org.quartz.JobExecutionContext;

import static de.codecentric.reedelk.scheduler.internal.attribute.SchedulerAttributes.FIRED_AT;

@Type
@TypeProperty(name = FIRED_AT, type = long.class)
public class SchedulerAttributes extends MessageAttributes {

    static final String FIRED_AT = "firedAt";

    public SchedulerAttributes(JobExecutionContext executionContext) {
        put(FIRED_AT, executionContext.getFireTime().getTime());
    }
}
