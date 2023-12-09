package it.unisa.ifttt_group_9.Trigger;

import it.unisa.ifttt_group_9.exceptions.IllegalTimeException;

import java.time.LocalDateTime;

//Concrete Product (AbstractFactory)
public class TriggerDay extends TriggerDecorator {

    private int dayWeek;

    public TriggerDay (int dayWeek, boolean negate, Trigger trigger, boolean nextTriggerAndOr) throws IllegalTimeException {
        super(negate, trigger, nextTriggerAndOr);


        this.dayWeek=dayWeek;
    }


    public TriggerDay (int dayWeek, boolean negate) throws IllegalTimeException {
        super(negate);


        this.dayWeek=dayWeek;
    }

    public TriggerDay (int dayWeek) throws IllegalTimeException {


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
