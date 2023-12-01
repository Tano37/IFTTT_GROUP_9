package it.unisa.ifttt_group_9;

// Classe TriggerFactory
public class TriggerFactory {


    public Trigger createTrigger(int hours, int minutes) {

        return new TriggerTimestamp(hours, minutes);
    }
    public Trigger createTrigger(int hours, int minutes,int dayWeek) {

        return new TriggerDay(hours, minutes, dayWeek);
    }

    public Trigger createTrigger(int hours, int minutes,int dayWeek,int dayMonth) {

        return new TriggerMonth(hours, minutes, dayWeek,dayMonth);
    }

}
