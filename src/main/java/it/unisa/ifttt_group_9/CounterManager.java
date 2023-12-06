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

    public int getCounterValue(String counterName){
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
                /*
                if errore che non esiste la variabile fare return stringa di avviso impostata;
                 */
            }
            else{
                elaboratedString.concat(expressionItem + " ");
            }
        }
        return elaboratedString;
    }

    @Override
    public String toString(){ return counterList.toString(); }
}
