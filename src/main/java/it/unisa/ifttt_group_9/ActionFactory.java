package it.unisa.ifttt_group_9;

//Abstract Factory
public interface ActionFactory {
    ActionText createAction(String text);
}
