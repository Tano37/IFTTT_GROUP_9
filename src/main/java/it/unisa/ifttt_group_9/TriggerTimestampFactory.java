package it.unisa.ifttt_group_9;

//Concrete Factory (AbstractFactory)
public class TriggerTimestampFactory implements TriggerFactory {
    @Override
    public TriggerTimestamp createTrigger(int hours, int minutes) {
        return new TriggerTimestamp(hours, minutes);
    }
}
