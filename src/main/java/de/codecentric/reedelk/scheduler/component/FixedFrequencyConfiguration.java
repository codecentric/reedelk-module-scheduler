package de.codecentric.reedelk.scheduler.component;

import de.codecentric.reedelk.runtime.api.annotation.*;
import de.codecentric.reedelk.runtime.api.component.Implementor;
import org.osgi.service.component.annotations.Component;

import static org.osgi.service.component.annotations.ServiceScope.PROTOTYPE;

@Component(service = FixedFrequencyConfiguration.class, scope = PROTOTYPE)
public class FixedFrequencyConfiguration implements Implementor {

    @Property("Frequency")
    @Hint("1000")
    @Example("2500")
    @InitValue("1000")
    @DefaultValue("1000")
    @Description("The frequency at which the Scheduler triggers the flow. " +
            "The value in this field depends on the <i>Time Unit</i> property.")
    private int period = 1000;

    @Property("Time unit")
    @Example("SECONDS")
    @InitValue("MILLISECONDS")
    @DefaultValue("MILLISECONDS")
    @Description("The Time Unit the Frequency value is expressed. " +
            "Possible values are: <b>MILLISECONDS</b>, <b>HOURS</b>, <b>MINUTES</b>, <b>SECONDS</b>.")
    private TimeUnit timeUnit;

    @Property("Start delay")
    @Hint("1500")
    @Example("1500")
    @DefaultValue("0")
    @Description("The amount of time to wait before triggering the first event.")
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
