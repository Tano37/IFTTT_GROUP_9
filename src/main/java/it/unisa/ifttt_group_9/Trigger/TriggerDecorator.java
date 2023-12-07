package it.unisa.ifttt_group_9.Trigger;




public class TriggerDecorator implements Trigger{

     boolean negate = false;
     boolean precTriggerAndOr = Trigger.AND;

     protected Trigger precTrigger = null;

    public TriggerDecorator(boolean negate) {
        this.negate = negate;
    }

    public TriggerDecorator(){
    }

    public TriggerDecorator(boolean negate, Trigger newTrigger, boolean operator){
        this.negate = negate;
        precTrigger = newTrigger;
        precTriggerAndOr = operator;
    }

    public boolean evaluate() {
        if(precTrigger !=null) {
            return precTrigger.evaluate();
        }
        return precTriggerAndOr;
    }

    @Override
    public void negate() {
        this.negate = true;
    }
}
