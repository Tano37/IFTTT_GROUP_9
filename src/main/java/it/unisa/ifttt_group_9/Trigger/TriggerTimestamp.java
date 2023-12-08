package it.unisa.ifttt_group_9.Trigger;

import it.unisa.ifttt_group_9.exceptions.IllegalTimeException;

import java.time.LocalTime;

//Concrete Product (AbstractFactory)
public class TriggerTimestamp extends TriggerDecorator {
    int hour;
    int minute;

    public TriggerTimestamp (int hour, int minute, boolean negate, Trigger trigger, boolean nextTriggerAndOr) {
        super(negate, trigger, nextTriggerAndOr);
        if ((hour < 0 || hour > 23) || (minute < 0 || minute > 59) )
            throw new IllegalTimeException();
        this.hour=hour;
        this.minute=minute;
    }

    public TriggerTimestamp (int hour, int minute, boolean negate) throws IllegalTimeException {
        super(negate);
        if ((hour < 0 || hour > 23) || (minute < 0 || minute > 59) )
            throw new IllegalTimeException();
        this.hour=hour;
        this.minute=minute;
    }

    public TriggerTimestamp (int hour, int minute) throws IllegalTimeException {
        if ((hour < 0 || hour > 23) || (minute < 0 || minute > 59) )
            throw new IllegalTimeException();
        this.hour=hour;
        this.minute=minute;
    }


    public void setTime (int hour, int minute){
        this.hour=hour;
        this.minute=minute;
    }


    @Override
    public boolean evaluate(){
        LocalTime now= LocalTime.now();
        int h= now.getHour();
        int m= now.getMinute();
        if(precTriggerAndOr){
            return negate != (h==this.hour && m==this.minute) && super.evaluate();
        }else {
            return negate != (h==this.hour && m==this.minute) || super.evaluate();
        }


    }

    public int getHour() {
        return hour;
    }

    public int getMinute() {
        return minute;
    }

    @Override
    public String toString() {
        return "TriggerTimestamp{" +
                "hour=" + hour +
                ", minute=" + minute +
                ", negate=" + negate +
                ", precTriggerAndOr=" + precTriggerAndOr +
                "\n" + precTrigger ;
    }
}
