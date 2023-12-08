package it.unisa.ifttt_group_9;

import it.unisa.ifttt_group_9.Rule.Rule;
import it.unisa.ifttt_group_9.Rule.RuleManager;

import java.util.ArrayList;
import java.util.Iterator;
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

    public void addCounter(Counter counter) {
        counterList.add(counter);
    }

    public List<Counter> getCounterList() {
        return counterList;
    }

    public Counter getCounterByName(String counterName){
        for (Counter element : getCounterList()) {
            if (element.getName().equals(counterName))
                return element;
        }
        return null;
    }


    public static String counterSubstitution(String text){
        String[] items = text.split(" ");

        String elaboratedString = "";
        for (String item : items) {
            //System.out.println("item: " + item);
            if(item.startsWith("$")){
                String variableName = item.substring(1);
                //System.out.println("substring: " + item.substring(1));
                //System.out.println("valore del substring: " + CounterManager.getInstance().getCounterByName(variableName).getValue());
                Counter counterToSubstitute= CounterManager.getInstance().getCounterByName(variableName);
                if (counterToSubstitute == null)
                    return ("Almeno un counter desiderato non è disponibile non è disponibile per l'output: " + text);
                elaboratedString=elaboratedString.concat(counterToSubstitute.getValue() + " ");
                //System.out.println(counterToSubstitute.getValue());
            }
            else{
                elaboratedString=elaboratedString.concat(item + " ");
                //System.out.println(expressionItem);
            }
        }
        System.out.println("eccola " + elaboratedString);
        return elaboratedString.trim();
    }

    @Override
    public String toString(){ return counterList.toString(); }
}
