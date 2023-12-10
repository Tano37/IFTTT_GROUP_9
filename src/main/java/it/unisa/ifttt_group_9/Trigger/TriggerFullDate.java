package it.unisa.ifttt_group_9.Trigger;

import it.unisa.ifttt_group_9.exceptions.IllegalTimeException;

import java.time.LocalDateTime;

//Concrete Product (AbstractFactory)
public class TriggerFullDate extends TriggerAbstractClass {
    private int hour;
    private int minute;

    private int dayWeek;
    private int dayMonth;

    private int year;

    public TriggerFullDate (int dayWeek, int dayMonth, int year, boolean negate, Trigger trigger, boolean nextTriggerAndOr) {
        super(negate, trigger, nextTriggerAndOr);
        if ((dayWeek<0 || dayWeek>31) || (dayMonth<1 || dayMonth>12) )
            throw new IllegalTimeException();


        this.dayWeek=dayWeek;
        this.dayMonth=dayMonth;
        this.year=year;
    }


    public TriggerFullDate (int dayWeek, int dayMonth, int year, boolean negate) throws IllegalTimeException {
        super(negate);
        if ((dayWeek<0 || dayWeek>31) || (dayMonth<1 || dayMonth>12) )
            throw new IllegalTimeException();

        this.dayWeek=dayWeek;
        this.dayMonth=dayMonth;
        this.year=year;
    }

    public TriggerFullDate (int dayWeek, int dayMonth, int year) throws IllegalTimeException {
        if ((dayWeek<0 || dayWeek>31) || (dayMonth<1 || dayMonth>12) )
            throw new IllegalTimeException();

        this.dayWeek=dayWeek;
        this.dayMonth=dayMonth;
        this.year=year;
    }


    public void setTime (int hour, int minute){
        this.hour=hour;
        this.minute=minute;
    }

    //check if the date is equal to the current one
    @Override
    public boolean evaluate(){
        LocalDateTime now= LocalDateTime.now();

        int dayNow= now.getDayOfMonth();
        int monthNow=now.getMonthValue();
        int yearNow=now.getYear();


        if(precTriggerAndOr){
            return negate != ( dayNow == this.dayWeek && monthNow == this.dayMonth && yearNow == this.year) && super.evaluate();
        }else {
            return negate != ( dayNow == this.dayWeek && monthNow == this.dayMonth && yearNow == this.year) || super.evaluate();
        }
    }


    @Override
    public String toString() {
        return (negate?"!" : "") + "{TriggerFullDate: " + dayWeek + "\\" + dayMonth +"\\" + year+ " }\n"  +super.toString();
    }
}
