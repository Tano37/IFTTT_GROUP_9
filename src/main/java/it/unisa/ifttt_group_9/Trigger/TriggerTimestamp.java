package it.unisa.ifttt_group_9.Trigger;

import it.unisa.ifttt_group_9.Trigger.Trigger;
import it.unisa.ifttt_group_9.exceptions.IllegalTimeException;

import java.io.Serializable;
import java.time.LocalTime;

//Concrete Product (AbstractFactory)
public class TriggerTimestamp extends AbstractTriggerDecorator {
    int hour;
    int minute;

    public TriggerTimestamp (int hour, int minute, boolean negate) throws IllegalTimeException {
        super(negate);
        if ((hour < 0 || hour > 23) || (minute < 0 || minute > 59) )
            throw new IllegalTimeException();
        this.hour=hour;
        this.minute=minute;
    }

    public TriggerTimestamp (int hour, int minute) throws IllegalTimeException {
        super(false);
        if ((hour < 0 || hour > 23) || (minute < 0 || minute > 59) )
            throw new IllegalTimeException();
        this.hour=hour;
        this.minute=minute;
    }

    /*
    public void setTime (int hour, int minute){
        this.hour=hour;
        this.minute=minute;
    }
    */

    @Override
    public boolean evaluate(){
        LocalTime now= LocalTime.now();
        int h= now.getHour();
        int m= now.getMinute();
        return negate != (h==this.hour && m==this.minute);
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
                '}';
    }
}
