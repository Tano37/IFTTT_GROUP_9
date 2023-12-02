package it.unisa.ifttt_group_9.Action;

import it.unisa.ifttt_group_9.Action.ActionFactory;
import it.unisa.ifttt_group_9.Action.ActionText;

public class ActionTextFactory implements ActionFactory {

    @Override
    public ActionText createAction(String text){
        return new ActionText(text);
    }
}
