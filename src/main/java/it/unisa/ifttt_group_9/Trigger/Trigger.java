package it.unisa.ifttt_group_9.Trigger;

import java.io.Serializable;

//Abstract Product (AbstractFactory)
public interface Trigger extends Serializable {
    boolean AND = true;
    boolean OR = false;
    public boolean evaluate();

    void negate();
}
