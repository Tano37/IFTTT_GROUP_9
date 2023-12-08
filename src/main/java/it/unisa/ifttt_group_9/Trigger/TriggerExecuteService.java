package it.unisa.ifttt_group_9.Trigger;

import javafx.concurrent.Service;
import javafx.concurrent.Task;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TriggerExecuteService  extends Service<Void> {
    private TriggerExitStatus triggerSelected;

    public TriggerExecuteService(TriggerExitStatus triggerSelected) {
        this.triggerSelected = triggerSelected;
    }


    @Override
    protected Task<Void> createTask() {
        return new Task<>() {
            @Override
            protected Void call() throws Exception {

                //Conversion of Command Line string into an a collection of commands
                String[] elements = triggerSelected.getCommandLine().split(",");

                ArrayList<String> lineCommands = new ArrayList<>();
                for (String element : elements) {
                    //Removing eventual spaces before and after the single commands
                    String trimmedElement = element.trim();
                    lineCommands.add(trimmedElement);
                }

                // Adding executable program path and related line commands to command
                List<String> command = new ArrayList<>();
                command.add(triggerSelected.getStringPath());
                command.addAll(lineCommands);

                //Building a process based on command
                ProcessBuilder processBuilder = new ProcessBuilder(command);

                //Starting the process and setting a trigger variable to its return value
                Process process = processBuilder.start();
                triggerSelected.setExitValue(process.waitFor());


/* This part is only needed to feedback how and why the process executed exits;

                StringBuilder standardOutput = new StringBuilder();
                StringBuilder errorOutput = new StringBuilder();

                try (BufferedReader standardReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
                     BufferedReader errorReader = new BufferedReader(new InputStreamReader(process.getErrorStream()))) {

                    // Extracting process standard output
                    String standardLine;
                    while ((standardLine = standardReader.readLine()) != null) {
                        standardOutput.append(standardLine).append("\n");
                    }

                    // Extracting process error output
                    String errorLine;
                    while ((errorLine = errorReader.readLine()) != null) {
                        errorOutput.append(errorLine).append("\n");
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }
                if (triggerSelected.getExitValue() == 0){
                    String processStandardOutput = standardOutput.toString();
                    System.out.println("Output standard del processo:\n" + processStandardOutput);
                }else{
                    String processErrorOutput = errorOutput.toString();
                    System.err.println("Output degli errori del processo:\n" + processErrorOutput);
                }
                

*/

                return null;
            }
        };


    }


}
