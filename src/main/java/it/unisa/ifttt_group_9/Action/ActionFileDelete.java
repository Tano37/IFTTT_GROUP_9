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
        return "ActionFileDelete{" +
                "filePath='" + filePath + '\'' +
                "\n" + tempAction +
                '}';
    }
}
