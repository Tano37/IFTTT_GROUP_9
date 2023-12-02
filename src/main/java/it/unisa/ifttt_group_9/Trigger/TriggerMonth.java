package it.unisa.ifttt_group_9.Trigger;

import it.unisa.ifttt_group_9.Trigger.Trigger;
import it.unisa.ifttt_group_9.exceptions.IllegalTimeException;

import java.io.Serializable;
import java.time.LocalDateTime;

//Concrete Product (AbstractFactory)
public class TriggerMonth implements Trigger {
    private int hour;
    private int minute;

    private int dayWeek;
    private int dayMonth;

    public TriggerMonth (int hour, int minute,int dayWeek, int dayMonth) throws IllegalTimeException {
        if ((hour < 0 || hour > 23) || (minute < 0 || minute > 59) || (dayMonth<0 || dayMonth>31) )
            throw new IllegalTimeException();
        this.hour=hour;
        this.minute=minute;
        this.dayWeek=dayWeek;
        this.dayMonth=dayMonth;
    }

    /*
    public void setTime (int hour, int minute){
        this.hour=hour;
        this.minute=minute;
    }
    */

    @Override
    public boolean evaluate(){
        LocalDateTime now= LocalDateTime.now();
        int h= now.getHour();
        int m= now.getMinute();
        int d= now.getDayOfMonth();

        return (h==this.hour && m==this.minute && d==this.dayMonth && this.dayWeek==0);
    }

    public int getHour() {
        return hour;
    }

    public int getMinute() {
        return minute;
    }

    public int getDay(){return dayMonth;}
}
