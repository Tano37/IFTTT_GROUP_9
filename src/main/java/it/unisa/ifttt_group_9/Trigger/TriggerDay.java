package it.unisa.ifttt_group_9.Trigger;

import it.unisa.ifttt_group_9.exceptions.IllegalTimeException;

import java.time.LocalDateTime;

//Concrete Product (AbstractFactory)
public class TriggerDay extends TriggerDecorator {
    private int hour;
    private int minute;
    private int dayWeek;

    public TriggerDay (int hour, int minute, int dayWeek, boolean negate, Trigger trigger, boolean nextTriggerAndOr) throws IllegalTimeException {
        super(negate, trigger, nextTriggerAndOr);
        if ((hour < 0 || hour > 23) || (minute < 0 || minute > 59) )
            throw new IllegalTimeException();
        this.hour=hour;
        this.minute=minute;
        this.dayWeek=dayWeek;
    }


    public TriggerDay (int hour, int minute, int dayWeek, boolean negate) throws IllegalTimeException {
        super(negate);
        if ((hour < 0 || hour > 23) || (minute < 0 || minute > 59) )
            throw new IllegalTimeException();
        this.hour=hour;
        this.minute=minute;
        this.dayWeek=dayWeek;
    }

    public TriggerDay (int hour, int minute, int dayWeek) throws IllegalTimeException {
        if ((hour < 0 || hour > 23) || (minute < 0 || minute > 59) )
            throw new IllegalTimeException();
        this.hour=hour;
        this.minute=minute;
        this.dayWeek=dayWeek;
    }



    @Override
    public boolean evaluate(){
        LocalDateTime now= LocalDateTime.now();
        int h= now.getHour();
        int m= now.getMinute();
        int d= now.getDayOfWeek().getValue();
        if(precTriggerAndOr){
            return negate != (h == this.hour && m == this.minute && d == this.dayWeek) && super.evaluate();
        }else{
            return negate != (h == this.hour && m == this.minute && d == this.dayWeek) || super.evaluate();

        }
    }

    public int getHour() {
        return hour;
    }

    public int getMinute() {
        return minute;
    }

    public int getDay(){return dayWeek;}

    @Override
    public String toString() {
        return (negate?"!" : "") + "{TriggerDay: " + dayWeek + " }\n" + super.toString();
    }
}
