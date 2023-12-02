package it.unisa.ifttt_group_9.Action;

import it.unisa.ifttt_group_9.Action.Action;

import javax.swing.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class ActionFileAddString implements Action {

    String filePath;
    String stringToAdd;

    public ActionFileAddString(String filePath, String stringToAdd) {
        this.filePath = filePath;
        this.stringToAdd = stringToAdd;
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



                    JOptionPane.showConfirmDialog(null, "The string has been added", "StringAddition", JOptionPane.DEFAULT_OPTION);
                    // Close theBufferedWriter
                    bufferedWriter.close();

                } catch (IOException e) {
                    System.err.println("Error during the adding of the string " + e.getMessage());
                }
    }
}
