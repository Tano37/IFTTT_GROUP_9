package it.unisa.ifttt_group_9.Trigger;

import it.unisa.ifttt_group_9.Counter;

public class TriggerCounterCompareCounter implements Trigger{
    private Counter counter1;
    private Counter counter2;
    private String valueOfComparation;

    public TriggerCounterCompareCounter(Counter counter1, Counter counter2, String valueOfComparation) {
        this.counter1=counter1;
        this.counter2=counter2;
        this.valueOfComparation=valueOfComparation;
    }

    @Override
    public boolean evaluate() {
        if(valueOfComparation.equals("greater")){
            return this.counter1.getValue()>counter2.getValue();
        }
        else if (valueOfComparation.equals("less")) {
            return this.counter1.getValue()<counter2.getValue();
        }
        else if (valueOfComparation.equals("equal")) {
            return this.counter1.getValue()==counter2.getValue();

        }
        return false;
    }
}
