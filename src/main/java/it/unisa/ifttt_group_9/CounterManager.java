package it.unisa.ifttt_group_9;

import it.unisa.ifttt_group_9.Rule.Rule;
import it.unisa.ifttt_group_9.Rule.RuleManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CounterManager {
    //applicazione del pattern singleton
    private static CounterManager instance=null;
    public static CounterManager getInstance() {
        if (instance == null)
            instance = new CounterManager();
        return instance;
    }
    private Map<String, Counter> counterMap;

    private CounterManager() {
        counterMap = new HashMap<>(); //conviene un altra struttura dati?
    }

    public void addRule(Counter counter) {
        counterMap.put(counter.getName(), counter);
    }

    public Map<String, Counter> getCounterMap() {
        return counterMap;
    }

    /* public int getCounterValue(String counterName){
        return counterList.
    }

    public static String counterSubstitution(String text){
        String[] items = text.split(" ");

        String elaboratedString = null;
        // Scorri e stampa le parole
        for (String item : items) {
            String expressionItem = null;
            String variableName = null;
            if(item.startsWith("$")){
                variableName = item.substring(1);
                elaboratedString.concat(getCounterValue(variableName) + " ");

                //if errore che non esiste la variabile fare return stringa di avviso impostata;

            }
            else{
                elaboratedString.concat(expressionItem + " ");
            }
        }
        return elaboratedString;
    }*/

    @Override
    public String toString(){ return counterMap.toString(); }
}
