package it.unisa.ifttt_group_9.Action;

import it.unisa.ifttt_group_9.Controller.PanelPopUPManager;
import it.unisa.ifttt_group_9.Counters.CounterManager;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class ActionFileAddString extends ActionAbstractClass {

    private String filePath;
    private String stringToAdd;
    private Boolean variableSubstitution;


    public ActionFileAddString(String filePath, String stringToAdd, Action action, Boolean variableSubstitution) {
        super(action);
        this.filePath = filePath;
        this.stringToAdd = stringToAdd;
        this.variableSubstitution=variableSubstitution;
    }
    public ActionFileAddString(String filePath, String stringToAdd, Boolean variabileSubstitution) {
        super(null);
        this.filePath = filePath;
        this.stringToAdd = stringToAdd;
        this.variableSubstitution=variabileSubstitution;
    }

    /*Checks whether variable substitution is enabled (this.variableSubstitution).
    If enabled, replaces the variables in the string to be added using CounterManager.
    counterSubstitution. Attempts to add the modified string to a file specified by filePath.
    If successful, displays a pop-up message indicating that the string has been added.
    It catches and handles any IOException that might occur during the operation on the file.
    In the final clause, the super function is called for any related actions after*/
    @Override
    public void executeAction() {
        if (this.variableSubstitution) {
            this.stringToAdd = CounterManager.counterSubstitution(this.stringToAdd);
        }

        try {

                   // Opens the file with th option to append on true
                    FileWriter fileWriter = new FileWriter(filePath, true);

                    // BufferWriter to write on the file
                    BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

                    // Adds the string
                    bufferedWriter.write("\n"+stringToAdd);


                    new PanelPopUPManager("StringAddition", "The string has been added").showMessage();

                    // Close theBufferedWriter
                    bufferedWriter.close();

                } catch (IOException e) {
                    System.err.println("Error during the adding of the string " + e.getMessage());
                } finally {
                    super.executeAction();
                }
    }

    @Override
    public String toString() {
        return "{ActionFileAddString: " + stringToAdd + " }\n" + super.toString();
    }
}
