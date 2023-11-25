package it.unisa.ifttt_group_9;

import java.io.Serializable;

public class Rule implements Serializable {
    private String ruleName;
    private Trigger ruleTrigger;
    private Action ruleAction;

    private Boolean launched = false;

    public Rule(String ruleName, Trigger ruleTrigger, Action ruleAction){
        this.ruleName=ruleName;
        this.ruleTrigger=ruleTrigger;
        this.ruleAction=ruleAction;
    }

    public String getRuleName() {
        return ruleName;
    }

    public Trigger getRuleTrigger() {
        return ruleTrigger;
    }

    public Action getRuleAction() {
        return ruleAction;
    }

    @Override
    public String toString(){
        return "Rule: " + this.ruleName + "" + this.ruleTrigger.toString() + "" + this.ruleAction.toString();
    }

    public Boolean getLaunched(){
        return launched;
    }

    public void setLaunched(Boolean launched){
        this.launched = launched;
    }


}
