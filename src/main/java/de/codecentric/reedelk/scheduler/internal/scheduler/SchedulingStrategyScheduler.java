package de.codecentric.reedelk.scheduler.internal.scheduler;

import de.codecentric.reedelk.runtime.api.component.InboundEventListener;

public interface SchedulingStrategyScheduler {

    SchedulerJob schedule(InboundEventListener listener);
}
