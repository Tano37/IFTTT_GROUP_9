package it.unisa.ifttt_group_9.Trigger;

import it.unisa.ifttt_group_9.Counter;

public class TriggerCounter extends TriggerDecorator{
    private int integerInsertByUser;
    private Counter counterToCompare;
    private String valueOfComparation;

    public TriggerCounter(int integerInsertByUser, Counter counterToCompare, String valueOfComparation, boolean negate, Trigger trigger, boolean precTriggerAndOr) {
        super(negate, trigger, precTriggerAndOr);
        this.integerInsertByUser = integerInsertByUser;
        this.counterToCompare= counterToCompare;
        this.valueOfComparation=valueOfComparation;
    }

    public TriggerCounter(int integerInsertByUser, Counter counterToCompare, String valueOfComparation, boolean negate) {
        super(negate);
        this.integerInsertByUser = integerInsertByUser;
        this.counterToCompare= counterToCompare;
        this.valueOfComparation=valueOfComparation;
    }
    public TriggerCounter(int integerInsertByUser, Counter counterToCompare, String valueOfComparation) {
        super(false);
        this.integerInsertByUser = integerInsertByUser;
        this.counterToCompare= counterToCompare;
        this.valueOfComparation=valueOfComparation;
    }

    @Override
    public boolean evaluate() {
        switch (valueOfComparation) {
            case "greater" -> {
                if (precTriggerAndOr) {
                    return negate != this.integerInsertByUser > counterToCompare.getValue() && super.evaluate();

                } else {
                    return negate != this.integerInsertByUser > counterToCompare.getValue() || super.evaluate();
                }
            }
            case "less" -> {
                if (precTriggerAndOr) {
                    return negate != this.integerInsertByUser < counterToCompare.getValue() && super.evaluate();

                } else {
                    return negate != this.integerInsertByUser < counterToCompare.getValue() || super.evaluate();
                }
            }
            case "equal" -> {
                if (precTriggerAndOr) {
                    return negate != (this.integerInsertByUser == counterToCompare.getValue()) && super.evaluate();

                } else {
                    return negate != (this.integerInsertByUser == counterToCompare.getValue()) || super.evaluate();
                }
            }
        }
        return negate;
    }
}
