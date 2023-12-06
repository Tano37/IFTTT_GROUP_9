package it.unisa.ifttt_group_9.Action;

import it.unisa.ifttt_group_9.Action.Action;
import it.unisa.ifttt_group_9.Controller.PanelChooserManager;
import it.unisa.ifttt_group_9.Controller.PanelPopUPManager;

import javax.swing.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class ActionFileAddString implements Action {

    private String filePath;
    private String stringToAdd;
    private Boolean variabileSubstitution;

    public ActionFileAddString(String filePath, String stringToAdd, Boolean variabileSubstitution) {
        this.filePath = filePath;
        this.stringToAdd = stringToAdd;
        this.variabileSubstitution=variabileSubstitution;
    }

    public String getFilePath() {
        return filePath;
    }

    @Override
    public void executeAction() {

                try {

                    // Opens the file with th option to append on true
                    FileWriter fileWriter = new FileWriter(filePath, true);

                    // BufferWriter to write on the file
                    BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

                    // Adds the string
                    bufferedWriter.write(stringToAdd);


                    new PanelPopUPManager("StringAddition", "The string has been added").showMessage();

                    // Close theBufferedWriter
                    bufferedWriter.close();

                } catch (IOException e) {
                    System.err.println("Error during the adding of the string " + e.getMessage());
                }
    }
}
