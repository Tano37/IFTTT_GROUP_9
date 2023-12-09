package it.unisa.ifttt_group_9.Action;

import it.unisa.ifttt_group_9.Controller.PanelPopUPManager;
import it.unisa.ifttt_group_9.CounterManager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ActionFileLaunch extends ActionDecorator {

    private String filePath;
    private String arguments;
    private Boolean variableSubstitution;



    public ActionFileLaunch(String filePath, String arguments, Action action,Boolean variabileSubstitution) {
        super(action);
        this.filePath = filePath;
        this.arguments = arguments;
        this.variableSubstitution=variabileSubstitution;
    }

    public ActionFileLaunch(String filePath, String arguments,Boolean variabileSubstitution) {
        super(null);

        this.filePath = filePath;
        this.arguments = arguments;
        this.variableSubstitution=variableSubstitution;
    }

    /*This method allows an external process to run. If the variable substitution option (this.variableSubstitution) is enabled, it replaces the variables in the arguments with values calculated by CounterManager.counterSubstitution.
      Constructs a list of commands, including the path to the executable (filePath) and any specified arguments (arguments).
      Creates a process using the ProcessBuilder constructor.
      Starts the process.
      Optionally, waits for the process to complete using process.waitFor().
      Displays a pop-up indicating the exit code of the process after its execution.*/
    @Override
    public void executeAction() {

        if (this.variableSubstitution)
            this.arguments= CounterManager.counterSubstitution(this.arguments);
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

    @Override
    public String toString() {
        return "{ActionFileLaunch: " + filePath + " }\n" + super.toString();
    }
}
