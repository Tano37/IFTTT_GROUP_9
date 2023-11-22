package it.unisa.ifttt_group_9;

public class Rule {
    String ruleName;
    Trigger ruleTrigger;
    Action ruleAction;

    public Rule(String ruleName, Trigger ruleTrigger, Action ruleAction){
        this.ruleName=ruleName;
        this.ruleTrigger=ruleTrigger;
        this.ruleAction=ruleAction;

        //aggiunta automatica della regola alla lista delle regole
        RuleManager.getInstance().addRule(this);
    }
}
