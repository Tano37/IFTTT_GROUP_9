package it.unisa.ifttt_group_9;

//Abstract Factory
public interface TriggerFactory {
    TriggerTimestamp createTrigger(int hours, int minutes);  // Factory Method
    //triggerAltriTipi createTrigger(altri parametri) da implementare nelle specifiche concreteFactories
}
