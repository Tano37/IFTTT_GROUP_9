package it.unisa.ifttt_group_9.Action;

import it.unisa.ifttt_group_9.Action.Action;
import it.unisa.ifttt_group_9.Controller.PanelPopUPManager;

import javax.swing.*;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ActionFileLaunch implements Action {

    String filePath;
    String arguments;

    public ActionFileLaunch(String filePath, String arguments){
        this.filePath = filePath;
        this.arguments = arguments;
    }

    @Override
    public void executeAction() {
        // Command and arguments
        List<String> command = new ArrayList<>();
        command.add(filePath);  // Replace with the actual path to your executable
        command.add(arguments);


        // Create the process builder
        ProcessBuilder processBuilder = new ProcessBuilder(command);

        try {
            // Start the process
            Process process = processBuilder.start();

            // Optionally, you can wait for the process to complete
            int exitCode = process.waitFor();
            new PanelPopUPManager(this.getClass().getName(),"Exit Code: " + exitCode);


        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
