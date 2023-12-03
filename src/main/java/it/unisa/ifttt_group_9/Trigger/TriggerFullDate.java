package it.unisa.ifttt_group_9.Trigger;

import it.unisa.ifttt_group_9.Trigger.Trigger;
import it.unisa.ifttt_group_9.exceptions.IllegalTimeException;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

//Concrete Product (AbstractFactory)
public class TriggerFullDate implements Trigger {
    private int hour;
    private int minute;

    private int dayWeek;
    private int dayMonth;

    private int year;

    public TriggerFullDate (int hour, int minute,int dayWeek, int dayMonth, int year) throws IllegalTimeException {
        if ((hour < 0 || hour > 23) || (minute < 0 || minute > 59))
            throw new IllegalTimeException();

        this.hour=hour;
        this.minute=minute;
        this.dayWeek=dayWeek;
        this.dayMonth=dayMonth;
        this.year=year;
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
        int hourNow= now.getHour();
        int minuteNow= now.getMinute();
        int dayNow= now.getDayOfMonth();
        int monthNow=now.getMonthValue();
        int yearNow=now.getYear();



        return (hourNow == this.hour && minuteNow == this.minute && dayNow == this.dayWeek && monthNow == this.dayMonth && yearNow == this.year);
    }

    public int getHour() {
        return hour;
    }

    public int getMinute() {
        return minute;
    }

    public int getDay(){return dayMonth;}
}
