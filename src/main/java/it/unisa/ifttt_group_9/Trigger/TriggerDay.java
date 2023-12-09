package it.unisa.ifttt_group_9.Trigger;

import it.unisa.ifttt_group_9.exceptions.IllegalTimeException;

import java.time.LocalDateTime;

//Concrete Product (AbstractFactory)
public class TriggerDay extends TriggerDecorator {

    private int dayWeek;

    public TriggerDay (int dayWeek, boolean negate, Trigger trigger, boolean nextTriggerAndOr) throws IllegalTimeException {
        super(negate, trigger, nextTriggerAndOr);
        if ((dayWeek<1 || dayWeek>7))
            throw new IllegalTimeException();

        this.dayWeek=dayWeek;
    }


    public TriggerDay (int dayWeek, boolean negate) throws IllegalTimeException {
        super(negate);
        if ((dayWeek<1 || dayWeek>7))
            throw new IllegalTimeException();

        this.dayWeek=dayWeek;
    }

    public TriggerDay (int dayWeek) throws IllegalTimeException {
        if ((dayWeek<1 || dayWeek>7))
            throw new IllegalTimeException();


        this.dayWeek=dayWeek;
    }



    @Override
    public boolean evaluate(){
        LocalDateTime now= LocalDateTime.now();

        int d= now.getDayOfWeek().getValue();
        if(precTriggerAndOr){
            return negate != (d == this.dayWeek) && super.evaluate();
        }else{
            return negate != (d == this.dayWeek) || super.evaluate();

        }
    }





    public int getDay(){return dayWeek;}

    @Override
    public String toString() {
        return (negate?"!" : "") + "{TriggerDay: " + dayWeek + " }\n" + super.toString();
    }
}
