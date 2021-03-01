package de.codecentric.reedelk.scheduler.internal.scheduler;

import de.codecentric.reedelk.scheduler.component.CronConfiguration;
import de.codecentric.reedelk.scheduler.component.FixedFrequencyConfiguration;
import de.codecentric.reedelk.scheduler.component.Scheduler;
import de.codecentric.reedelk.scheduler.component.SchedulingStrategy;

import static de.codecentric.reedelk.runtime.api.commons.ComponentPrecondition.Configuration.requireNotNull;
import static de.codecentric.reedelk.runtime.api.commons.ComponentPrecondition.Configuration.requireTrue;
import static de.codecentric.reedelk.scheduler.internal.scheduler.Messages.Scheduler.*;

public class SchedulingStrategyBuilder {

    private SchedulingStrategy strategy;
    private CronConfiguration cronConfig;
    private FixedFrequencyConfiguration fixedFrequencyConfig;

    private SchedulingStrategyBuilder(SchedulingStrategy schedulingStrategy) {
        this.strategy = schedulingStrategy;
    }

    public SchedulingStrategyBuilder withFixedFrequencyConfig(FixedFrequencyConfiguration fixedFrequencyConfiguration) {
        this.fixedFrequencyConfig = fixedFrequencyConfiguration;
        return this;
    }

    public SchedulingStrategyBuilder withFixedFrequencyConfig(CronConfiguration cronConfiguration) {
        this.cronConfig = cronConfiguration;
        return this;
    }

    public SchedulingStrategyScheduler build() {
        String message = ERROR_CONFIG_SCHEDULING_STRATEGY.format(strategy);
        boolean isAcceptedStrategy =
                SchedulingStrategy.FIXED_FREQUENCY.equals(strategy) ||
                        SchedulingStrategy.CRON.equals(strategy);
        requireTrue(Scheduler.class, isAcceptedStrategy, message);

        if (SchedulingStrategy.FIXED_FREQUENCY.equals(strategy)) {
            message = ERROR_CONFIG_FIXED_FREQUENCY_MISSING.format(SchedulingStrategy.FIXED_FREQUENCY.toString());
            requireNotNull(Scheduler.class, fixedFrequencyConfig, message);
            return new SchedulingStrategySchedulerFixedFrequency(fixedFrequencyConfig);

        } else {
            message = ERROR_CONFIG_CRON_MISSING.format(SchedulingStrategy.CRON.toString());
            requireNotNull(Scheduler.class, cronConfig, message);
            return new SchedulingStrategySchedulerCron(cronConfig);
        }
    }

    public static SchedulingStrategyBuilder get(SchedulingStrategy schedulingStrategy) {
        return new SchedulingStrategyBuilder(schedulingStrategy);
    }
}
