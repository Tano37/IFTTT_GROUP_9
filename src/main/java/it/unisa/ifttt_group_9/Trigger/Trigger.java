package it.unisa.ifttt_group_9.Trigger;

import java.io.Serializable;

//Abstract Product (AbstractFactory)
public interface Trigger extends Serializable {
    public boolean evaluate();
}