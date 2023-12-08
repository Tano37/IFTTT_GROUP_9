package it.unisa.ifttt_group_9.Trigger;

import it.unisa.ifttt_group_9.Counter;

public class TriggerCounterCompareCounter extends TriggerDecorator{
    private Counter counter1;
    private Counter counter2;
    private String valueOfComparation;

    public TriggerCounterCompareCounter(Counter counter1, Counter counter2, String valueOfComparation) {
        this.counter1=counter1;
        this.counter2=counter2;
        this.valueOfComparation=valueOfComparation;
    }

    public TriggerCounterCompareCounter(Counter counter1, Counter counter2, String valueOfComparationboolean,
                                        boolean negate , Trigger newTrigger, boolean operator) {
        super(negate, newTrigger, operator);
        this.counter1 = counter1;
        this.counter2 = counter2;
        this.valueOfComparation = valueOfComparationboolean;
    }

    public TriggerCounterCompareCounter(Counter counter1, Counter counter2,
                                        String valueOfComparation,boolean negate ) {
        super(negate);
        this.counter1 = counter1;
        this.counter2 = counter2;
        this.valueOfComparation = valueOfComparation;
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
