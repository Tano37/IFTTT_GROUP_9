package it.unisa.ifttt_group_9.Action;

import it.unisa.ifttt_group_9.Action.Action;
import it.unisa.ifttt_group_9.Controller.PanelPopUPManager;

import javax.swing.*;
import java.io.*;

public class ActionFileDelete implements Action {

    String filePath;

    public ActionFileDelete(String filePath){
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
    }
}
