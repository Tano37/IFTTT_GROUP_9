package it.unisa.ifttt_group_9.Action;

import it.unisa.ifttt_group_9.Controller.PanelPopUPManager;

import java.io.File;

public class ActionFileDelete extends ActionDecorator {

    String filePath;

    public ActionFileDelete(String filePath, Action action) {
        super(action);
        this.filePath = filePath;
    }
    public ActionFileDelete(String filePath){
        super(null);
        this.filePath = filePath;
    }

    /*This method deletes a file by taking the path passed at the time of cration of the action
      and if it is present it shows a success message while if it does not exist it shows an error message.
      In the final clause, the super function is called for any related actions after*/
    @Override
    public void executeAction() {
        File file = new File(filePath);

        // Remove the file
        if (file.delete()) {
            new PanelPopUPManager(this.getClass().getName(),"The file has been removed");

        } else {
            new PanelPopUPManager(this.getClass().getName(),"The file HASN'T been removed");

        }
        super.executeAction();
    }

    @Override
    public String toString() {
        return "{ActionFileDelete: " + filePath + " }\n" + super.toString();
    }
}
