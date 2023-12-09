package it.unisa.ifttt_group_9.Action;

public abstract class ActionDecorator implements Action {

    protected Action tempAction;

    public ActionDecorator(Action newAction) {
        tempAction = newAction;
    }

    @Override
    public void executeAction() {
        if (tempAction != null) {
            tempAction.executeAction();
        }


    }

    public String toString(){
        if (tempAction!=null){
            return tempAction.toString();
        }else {
            return "";
        }
    }
}
