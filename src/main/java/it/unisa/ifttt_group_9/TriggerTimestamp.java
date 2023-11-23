package it.unisa.ifttt_group_9;

import it.unisa.ifttt_group_9.exceptions.IllegalTimeException;

import java.time.LocalTime;

//Concrete Product (AbstractFactory)
public class TriggerTimestamp implements Trigger {
    int hour;
    int minute;

    public TriggerTimestamp (int hour, int minute) throws IllegalTimeException {
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
        return (h==this.hour && m==this.minute);
    }
}
