package it.unisa.ifttt_group_9.Trigger;

import it.unisa.ifttt_group_9.Action.Action;
import it.unisa.ifttt_group_9.Action.ActionAudio;
import it.unisa.ifttt_group_9.Action.ActionFileLaunch;
import it.unisa.ifttt_group_9.Action.ActionText;

class MainTriggerDecoratorTest {

    public static void main(String[] args){
        Trigger selectedTrigger = new TriggerTimestamp(21,31);
        selectedTrigger.negate();
        selectedTrigger = new TriggerTimestamp(21, 32, false, selectedTrigger, Trigger.OR);
        selectedTrigger.negate();
        selectedTrigger = new TriggerTimestamp(21, 44, false, selectedTrigger, Trigger.OR);

        System.out.println(selectedTrigger.evaluate());
    }




}
