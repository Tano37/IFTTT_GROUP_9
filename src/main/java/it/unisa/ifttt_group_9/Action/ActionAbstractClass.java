// This abstract class is part of the decorator pattern for actions.
// It implements the Action interface and acts as a base class for concrete decorators.
package it.unisa.ifttt_group_9.Action;

public abstract class ActionAbstractClass implements Action {

    protected Action tempAction;

    public ActionAbstractClass(Action newAction) {
        tempAction = newAction;
    }

    // Implements the executeAction method from the Action interface.
    // Delegates the execution to the wrapped Action if it exists.
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
