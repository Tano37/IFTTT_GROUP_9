package it.unisa.ifttt_group_9;

// Classe TriggerFactory
public class TriggerFactory {


    public Trigger createTrigger(int hours, int minutes) {

        return new TriggerTimestamp(hours, minutes);
    }
    public Trigger createTrigger(int hours, int minutes,int day) {

        return new TriggerDay(hours, minutes, day);
    }

}
