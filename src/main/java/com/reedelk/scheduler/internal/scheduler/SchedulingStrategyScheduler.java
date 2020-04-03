package com.reedelk.scheduler.internal.scheduler;

import com.reedelk.runtime.api.component.InboundEventListener;

public interface SchedulingStrategyScheduler {

    SchedulerJob schedule(InboundEventListener listener);
}
