package de.codecentric.reedelk.scheduler.component;

import de.codecentric.reedelk.runtime.api.annotation.DisplayName;

public enum SchedulingStrategy {

    @DisplayName("Fixed Frequency")
    FIXED_FREQUENCY,
    @DisplayName("Cron")
    CRON
}
