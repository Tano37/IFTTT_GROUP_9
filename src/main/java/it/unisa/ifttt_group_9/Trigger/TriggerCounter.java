package it.unisa.ifttt_group_9.Trigger;

import it.unisa.ifttt_group_9.Counter;

public class TriggerCounter extends TriggerDecorator{
    private Counter counter1;
    private Counter counter2;
    private String valueOfComparation;

    public TriggerCounter(int counter1, Counter counter2, String valueOfComparation, boolean negate, Trigger trigger, boolean precTriggerAndOr) {
        super(negate, trigger, precTriggerAndOr);
        this.counter1 = new Counter("name1", counter1);
        this.counter2 = counter2;
        this.valueOfComparation=valueOfComparation;
    }

    public TriggerCounter(Counter counter1, Counter counter2, String valueOfComparation, boolean negate, Trigger trigger, boolean precTriggerAndOr) {
        super(negate, trigger, precTriggerAndOr);
        this.counter1 = counter1;
        this.counter2 = counter2;
        this.valueOfComparation=valueOfComparation;
    }

    public TriggerCounter(int counter1, Counter counter2, String valueOfComparation) {
        super(false);
        this.counter1 = new Counter("name1", counter1);
        this.counter2 = counter2;
        this.valueOfComparation=valueOfComparation;
    }

    public TriggerCounter(Counter counter1, Counter counter2, String valueOfComparation){
        this.counter1 = counter1;
        this.counter2 = counter2;
        this.valueOfComparation=valueOfComparation;
    }
    public TriggerCounter(int counter1, Counter counter2, String valueOfComparation, boolean negate) {
        super(negate);
        this.counter1 = new Counter("name1", counter1);
        this.counter2 = counter2;
        this.valueOfComparation=valueOfComparation;
    }

    public TriggerCounter(Counter counter1, Counter counter2, String valueOfComparation, boolean negate) {
        super(negate);
        this.counter1 = counter1;
        this.counter2 = counter2;
        this.valueOfComparation=valueOfComparation;
    }


    @Override
    public boolean evaluate() {
        switch (valueOfComparation) {
            case "greater" -> {
                if (precTriggerAndOr) {
                    return negate != this.counter1.getValue() > counter2.getValue() && super.evaluate();

                } else {
                    return negate != this.counter1.getValue() > counter2.getValue() || super.evaluate();
                }
            }
            case "less" -> {
                if (precTriggerAndOr) {
                    return negate != this.counter1.getValue() < counter2.getValue() && super.evaluate();

                } else {
                    return negate != this.counter1.getValue() < counter2.getValue() || super.evaluate();
                }
            }
            case "equal" -> {
                if (precTriggerAndOr) {
                    return negate != (this.counter1.getValue() == counter2.getValue()) && super.evaluate();

                } else {
                    return negate != (this.counter1.getValue() == counter2.getValue()) || super.evaluate();
                }
            }
        }
        return negate;
    }

    public String toString(){
        return (negate?"!" : "") + "{TriggerCounter: " + counter1.getValue() + " " + valueOfComparation + counter2.getValue()  + super.toString();
    }
}
