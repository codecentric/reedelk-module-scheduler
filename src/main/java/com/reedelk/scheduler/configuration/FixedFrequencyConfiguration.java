package com.reedelk.scheduler.configuration;

import com.reedelk.runtime.api.annotation.*;
import com.reedelk.runtime.api.component.Implementor;
import org.osgi.service.component.annotations.Component;

import static org.osgi.service.component.annotations.ServiceScope.PROTOTYPE;

@Component(service = FixedFrequencyConfiguration.class, scope = PROTOTYPE)
public class FixedFrequencyConfiguration implements Implementor {

    @Hint("1000")
    @Example("2500")
    @DefaultValue("1000")
    @InitValue("1000")
    @Property("Frequency")
    @PropertyDescription("The frequency at which the Scheduler triggers the flow. " +
            "The value in this field depends on the <i>Time Unit</i> property.")
    private int period = 1000;

    @InitValue("MILLISECONDS")
    @Example("SECONDS")
    @DefaultValue("MILLISECONDS")
    @Property("Time unit")
    @PropertyDescription("The Time Unit the Frequency value is expressed. " +
            "Possible values are: <b>MILLISECONDS</b>, <b>HOURS</b>, <b>MINUTES</b>, <b>SECONDS</b>.")
    private TimeUnit timeUnit;

    @Hint("1500")
    @Example("1500")
    @DefaultValue("0")
    @Property("Start delay")
    @PropertyDescription("The amount of time to wait before triggering the first event.")
    private int delay;

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
