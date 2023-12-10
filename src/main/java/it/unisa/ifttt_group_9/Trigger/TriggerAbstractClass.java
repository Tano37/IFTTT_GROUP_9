package it.unisa.ifttt_group_9.Trigger;




public class TriggerAbstractClass implements Trigger{

     boolean negate = false;
     boolean precTriggerAndOr = Trigger.AND;

     protected Trigger precTrigger = null;

    public TriggerAbstractClass(boolean negate) {
        this.negate = negate;
    }

    public TriggerAbstractClass(){
    }

    public TriggerAbstractClass(boolean negate, Trigger newTrigger, boolean operator){
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

    public String toString(){
        if (precTrigger!=null){
            return  (precTriggerAndOr? "AND":"OR") + "(" + precTrigger +  ")";
        }else {
            return "";
        }
    }

    public String getString(){
        return this.toString();
    }
}
