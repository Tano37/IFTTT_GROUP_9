package it.unisa.ifttt_group_9.Action;

import it.unisa.ifttt_group_9.Action.Action;

//Abstract Factory
public interface ActionFactory {
    Action createAction(String text);

}
