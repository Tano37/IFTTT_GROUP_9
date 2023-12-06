package it.unisa.ifttt_group_9.Trigger;

public class AbstractTriggerDecorator implements Trigger{

     boolean negate = false;

    public AbstractTriggerDecorator(boolean negate) {
        this.negate = negate;
    }

    public void setNegate(boolean negate){
        this.negate = negate;
    }

    public boolean evaluate() {
        return false;
    }
}
