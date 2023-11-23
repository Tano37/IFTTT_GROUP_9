package it.unisa.ifttt_group_9;

import java.util.ArrayList;
import java.util.List;

public class RuleManager {
    //applicazione del pattern singleton
    private static RuleManager instance=null;
    public static RuleManager getInstance() {
        if (instance == null)
            instance = new RuleManager();
        return instance;
    }
    private List<Rule> ruleList;

    private RuleManager() {
        ruleList = new ArrayList<>(); //conviene un altra struttura dati?
    }

    public void addRule(Rule rule) {
        ruleList.add(rule);
    }

    public List<Rule> getRuleList() {
        return ruleList;
    }

    @Override
    public String toString(){ return ruleList.toString(); }


}
