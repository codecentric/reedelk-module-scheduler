package com.reedelk.scheduler.internal.scheduler;

import com.reedelk.runtime.api.commons.FormattedMessage;

public class Messages {

    private Messages() {
    }

    public enum Scheduler implements FormattedMessage {

        ERROR_QUARTZ_SCHEDULER_DISPOSE("Could not dispose quartz scheduler: %s"),
        ERROR_QUARTZ_SCHEDULER_INIT("Could not initialize quartz scheduler: %s"),
        ERROR_SCHEDULE_JOB("Could not schedule job with id=[%s]: %s"),
        ERROR_DELETE_JOB("Could not delete job with id=[%s]: %s"),
        ERROR_QUARTZ_CONTEXT("Could not get quartz context: %s"),
        ERROR_CONFIG_FIXED_FREQUENCY_MISSING("Scheduler 'fixedFrequencyConfig' property must be defined in the JSON definition when 'strategy' is %s"),
        ERROR_CONFIG_CRON_MISSING("Scheduler 'cronConfig' property must be defined in the JSON definition when 'strategy' is %s"),
        ERROR_CONFIG_SCHEDULING_STRATEGY("Scheduler 'strategy' value=[%s] is not valid.");

        private String message;

        Scheduler(String message) {
            this.message = message;
        }

        @Override
        public String template() {
            return message;
        }
    }
}
