package it.unisa.ifttt_group_9.factories;

import it.unisa.ifttt_group_9.TriggerTimestamp;
import it.unisa.ifttt_group_9.Trigger;

public class TriggerTimestampFactory implements TriggerFactory {
private int minutes;
private int hours;

    public TriggerTimestampFactory(int hours, int minutes){
        this.hours=hours;
        this.minutes=minutes;
    }
    @Override
    public Trigger createTrigger() {
        return new TriggerTimestamp(this.hours, this.minutes);
    }

}
