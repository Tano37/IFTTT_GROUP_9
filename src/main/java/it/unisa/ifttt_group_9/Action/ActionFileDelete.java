package it.unisa.ifttt_group_9.Action;

import it.unisa.ifttt_group_9.Action.Action;

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
            JOptionPane.showConfirmDialog(null, "The file has been removed", "FileDelete", JOptionPane.DEFAULT_OPTION);

        } else {
            JOptionPane.showConfirmDialog(null, "The file HASN'T been removed", "FileDelete", JOptionPane.DEFAULT_OPTION);

        }
    }
}
