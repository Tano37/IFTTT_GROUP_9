package it.unisa.ifttt_group_9.Trigger;

import it.unisa.ifttt_group_9.exceptions.IllegalTimeException;

import java.time.LocalDateTime;

//Concrete Product (AbstractFactory)
public class TriggerMonth extends TriggerDecorator {
    private int hour;
    private int minute;

    private int dayWeek;
    private int dayMonth;

    public TriggerMonth (int hour, int minute,int dayWeek, int dayMonth, boolean negate, Trigger trigger, boolean nextTriggerAndOr) {
        super(negate, trigger, nextTriggerAndOr);
        if ((hour < 0 || hour > 23) || (minute < 0 || minute > 59) || (dayMonth<0 || dayMonth>31) )
            throw new IllegalTimeException();
        this.hour=hour;
        this.minute=minute;
        this.dayWeek=dayWeek;
        this.dayMonth=dayMonth;
    }
    public TriggerMonth (int hour, int minute,int dayWeek, int dayMonth, boolean negate) throws IllegalTimeException {
        super(negate);
        if ((hour < 0 || hour > 23) || (minute < 0 || minute > 59) || (dayMonth<0 || dayMonth>31) )
            throw new IllegalTimeException();
        this.hour=hour;
        this.minute=minute;
        this.dayWeek=dayWeek;
        this.dayMonth=dayMonth;
    }

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
        if(precTriggerAndOr){
            return negate != (h==this.hour && m==this.minute && d==this.dayMonth && this.dayWeek==0) && super.evaluate();
        }else {
            return negate != (h==this.hour && m==this.minute && d==this.dayMonth && this.dayWeek==0) || super.evaluate();
        }

<<<<<<< Updated upstream
        if (precTriggerAndOr) {
            return negate != (h==this.hour && m==this.minute && d==this.dayMonth && this.dayWeek==0) && super.evaluate();
        }else{
            return negate != (h==this.hour && m==this.minute && d==this.dayMonth && this.dayWeek==0) || super.evaluate();
        }

    }

    @Override
    public String toString() {
        return (negate?"!" : "") + "{TriggerMonth: "  + dayMonth + " }\n" +super.toString();
=======
>>>>>>> Stashed changes
    }

    public int getHour() {
        return hour;
    }

    public int getMinute() {
        return minute;
    }

    public int getDay(){return dayMonth;}
}
