package it.unisa.ifttt_group_9;

import java.io.IOException;
import java.io.Serializable;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class Rule implements Serializable {
    private String ruleName;
    private Trigger ruleTrigger;
    private Action ruleAction;

    //Activated/Deactivated
    private Boolean status;

    //Control Variable
    private Boolean launched = false;

    //Variable for Rule Sleeping
    private LocalDateTime dateUntilSleep;

    private boolean fireOnce;



    public Rule(String ruleName, Trigger ruleTrigger, Action ruleAction, Boolean fireOnce){
        this.ruleName=ruleName;
        this.ruleTrigger=ruleTrigger;
        this.ruleAction=ruleAction;
        this.status=true;
        this.dateUntilSleep=null;
        this.fireOnce=fireOnce;

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

    public boolean isFireOnce() {
        return fireOnce;
    }

    public void setDateUntilSleep(LocalDateTime dateUntilSleep) {
        this.dateUntilSleep = dateUntilSleep;
    }

    public LocalDateTime getDateUntilSleep() {
        return dateUntilSleep;
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

    public Boolean getStatus(){ return status; }

    public void setStatus(Boolean status){
        this.status = status;
    }

}
