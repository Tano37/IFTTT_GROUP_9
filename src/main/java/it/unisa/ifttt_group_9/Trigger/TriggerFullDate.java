package it.unisa.ifttt_group_9.Trigger;

import it.unisa.ifttt_group_9.exceptions.IllegalTimeException;

import java.time.LocalDateTime;

//Concrete Product (AbstractFactory)
public class TriggerFullDate extends TriggerDecorator {
    private int hour;
    private int minute;

    private int dayWeek;
    private int dayMonth;

    private int year;

    public TriggerFullDate (int dayWeek, int dayMonth, int year, boolean negate, Trigger trigger, boolean nextTriggerAndOr) {
        super(negate, trigger, nextTriggerAndOr);



        this.dayWeek=dayWeek;
        this.dayMonth=dayMonth;
        this.year=year;
    }


    public TriggerFullDate (int dayWeek, int dayMonth, int year, boolean negate) throws IllegalTimeException {
        super(negate);


        this.dayWeek=dayWeek;
        this.dayMonth=dayMonth;
        this.year=year;
    }

    public TriggerFullDate (int dayWeek, int dayMonth, int year) throws IllegalTimeException {


        this.dayWeek=dayWeek;
        this.dayMonth=dayMonth;
        this.year=year;
    }


    public void setTime (int hour, int minute){
        this.hour=hour;
        this.minute=minute;
    }


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

    public int getHour() {
        return hour;
    }

    public int getMinute() {
        return minute;
    }

    public int getDay(){return dayMonth;}

    @Override
    public String toString() {
        return (negate?"!" : "") + "{TriggerFullDate: " + dayWeek + "\\" + dayMonth +"\\" + year+ " }\n"  +super.toString();
    }
}
