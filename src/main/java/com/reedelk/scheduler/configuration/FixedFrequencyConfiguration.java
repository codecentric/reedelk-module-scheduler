package com.reedelk.scheduler.configuration;

import com.reedelk.runtime.api.annotation.Default;
import com.reedelk.runtime.api.annotation.Hint;
import com.reedelk.runtime.api.annotation.Property;
import com.reedelk.runtime.api.annotation.PropertyInfo;
import com.reedelk.runtime.api.component.Implementor;
import org.osgi.service.component.annotations.Component;

import static org.osgi.service.component.annotations.ServiceScope.PROTOTYPE;

@Component(service = FixedFrequencyConfiguration.class, scope = PROTOTYPE)
public class FixedFrequencyConfiguration implements Implementor {

    @Property("Frequency")
    @Default("1000")
    @Hint("1000")
    @PropertyInfo("The frequency at which the Scheduler triggers the flow. " +
            "The value in this field depends on the <i>Time Unit</i> selected below.")
    private int period;

    @Property("Start delay")
    @Hint("1500")
    @PropertyInfo("The amount of time to wait before triggering the first event.")
    private int delay;

    @Property("Time unit")
    @Default("MILLISECONDS")
    private TimeUnit timeUnit;

    public int getPeriod() {
        return period;
    }

    public void setPeriod(int period) {
        this.period = period;
    }

    public int getDelay() {
        return delay;
    }

    public void setDelay(int delay) {
        this.delay = delay;
    }

    public TimeUnit getTimeUnit() {
        return timeUnit;
    }

    public void setTimeUnit(TimeUnit timeUnit) {
        this.timeUnit = timeUnit;
    }
}
