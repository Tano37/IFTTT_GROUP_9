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
}
