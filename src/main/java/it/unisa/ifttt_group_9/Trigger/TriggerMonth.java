package it.unisa.ifttt_group_9.Trigger;

import it.unisa.ifttt_group_9.exceptions.IllegalTimeException;

import java.time.LocalDateTime;

//Concrete Product (AbstractFactory)
public class TriggerMonth extends TriggerDecorator {



    private int dayMonth;

    public TriggerMonth (int dayMonth, boolean negate, Trigger trigger, boolean nextTriggerAndOr) {
        super(negate, trigger, nextTriggerAndOr);
        if ((dayMonth<0 || dayMonth>31) )
            throw new IllegalTimeException();

        this.dayMonth=dayMonth;
    }
    public TriggerMonth (int dayMonth, boolean negate) throws IllegalTimeException {
        super(negate);
        if ((dayMonth<0 || dayMonth>31) )
            throw new IllegalTimeException();

        this.dayMonth=dayMonth;
    }

    public TriggerMonth (int dayMonth) throws IllegalTimeException {
        if ((dayMonth<0 || dayMonth>31) )
            throw new IllegalTimeException();

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

        int d= now.getDayOfMonth();



        if (precTriggerAndOr) {
            return negate != (d==this.dayMonth) && super.evaluate();
        }else{
            return negate != (d==this.dayMonth) || super.evaluate();
        }

    }

    @Override
    public String toString() {
        return (negate?"!" : "") + "{TriggerMonth: "  + dayMonth + " }\n" +super.toString();

    }



    public int getDay(){return dayMonth;}
}
