package com.reedelk.scheduler.internal.attribute;

import com.reedelk.runtime.api.annotation.Type;
import com.reedelk.runtime.api.annotation.TypeProperty;
import com.reedelk.runtime.api.message.MessageAttributes;
import org.quartz.JobExecutionContext;

import static com.reedelk.scheduler.internal.attribute.SchedulerAttributes.FIRED_AT;

@Type
@TypeProperty(name = FIRED_AT, type = long.class)
public class SchedulerAttributes extends MessageAttributes {

    static final String FIRED_AT = "firedAt";

    public SchedulerAttributes(JobExecutionContext executionContext) {
        put(FIRED_AT, executionContext.getFireTime().getTime());
    }
}
