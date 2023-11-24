package it.unisa.ifttt_group_9;

public class Rule {
    private String ruleName;
    private Trigger ruleTrigger;
    private Action ruleAction;

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
}
