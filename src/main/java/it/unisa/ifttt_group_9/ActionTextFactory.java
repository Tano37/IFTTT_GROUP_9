package it.unisa.ifttt_group_9;

public class ActionTextFactory implements ActionFactory{

    @Override
    public ActionText createAction(String text){
        return new ActionText(text);
    }
}
