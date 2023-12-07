package it.unisa.ifttt_group_9.Trigger;

import it.unisa.ifttt_group_9.Counter;

public class TriggerCounter extends TriggerDecorator{
    private int integerInsertByUser;
    private Counter counterToCompare;
    private String valueOfComparation;

    public TriggerCounter(int integerInsertByUser, Counter counterToCompare, String valueOfComparation) {
        this.integerInsertByUser = integerInsertByUser;
        this.counterToCompare= counterToCompare;
        this.valueOfComparation=valueOfComparation;
    }

    @Override
    public boolean evaluate() {
        if(valueOfComparation.equals("greater")){
            return this.integerInsertByUser>counterToCompare.getValue();
        }
        else if (valueOfComparation.equals("less")) {
            return this.integerInsertByUser<counterToCompare.getValue();
        }
        else if (valueOfComparation.equals("equal")) {
            return this.integerInsertByUser==counterToCompare.getValue();

        }
        return false;
    }
}
