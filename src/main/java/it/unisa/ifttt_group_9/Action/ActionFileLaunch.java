package it.unisa.ifttt_group_9.Action;

import it.unisa.ifttt_group_9.Controller.PanelPopUPManager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ActionFileLaunch extends ActionDecorator {

    private String filePath;
    private String arguments;
    private Boolean variabileSubstitution;


    public ActionFileLaunch(String filePath, String arguments, Action action,Boolean variabileSubstitution) {
        super(action);
        this.filePath = filePath;
        this.arguments = arguments;
        this.variabileSubstitution=variabileSubstitution;
    }

    public ActionFileLaunch(String filePath, String arguments,Boolean variabileSubstitution) {
        super(null);
        this.filePath = filePath;
        this.arguments = arguments;
        this.variabileSubstitution=variabileSubstitution;
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
            super.executeAction();

            // Optionally, you can wait for the process to complete
            int exitCode = process.waitFor();
            new PanelPopUPManager(this.getClass().getName(),"Exit Code: " + exitCode);


        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
