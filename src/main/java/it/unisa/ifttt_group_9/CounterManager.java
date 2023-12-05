package it.unisa.ifttt_group_9;

import it.unisa.ifttt_group_9.Rule.Rule;
import it.unisa.ifttt_group_9.Rule.RuleManager;

import java.util.ArrayList;
import java.util.List;

public class CounterManager {
    //applicazione del pattern singleton
    private static CounterManager instance=null;
    public static CounterManager getInstance() {
        if (instance == null)
            instance = new CounterManager();
        return instance;
    }
    private List<Counter> counterList;

    private CounterManager() {
        counterList = new ArrayList<>(); //conviene un altra struttura dati?
    }

    public void addRule(Counter counter) {
        counterList.add(counter);
    }

    public List<Counter> getCounterList() {
        return counterList;
    }

    @Override
    public String toString(){ return counterList.toString(); }
}