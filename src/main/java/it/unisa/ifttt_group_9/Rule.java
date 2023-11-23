package it.unisa.ifttt_group_9;

public class Rule {
    String ruleName;
    Trigger ruleTrigger;
    Action ruleAction;

    public Rule(String ruleName, Trigger ruleTrigger, Action ruleAction){
        this.ruleName=ruleName;
        this.ruleTrigger=ruleTrigger;
        this.ruleAction=ruleAction;
    }

    public String getRuleName() {
        return ruleName;
    }

    @Override
    public String toString(){
        return "Rule: " + this.ruleName + "" + this.ruleTrigger.toString() + "" + this.ruleAction.toString();
    }
}
