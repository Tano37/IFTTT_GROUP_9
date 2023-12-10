package it.unisa.ifttt_group_9.ActionTest;

import it.unisa.ifttt_group_9.Action.ActionFileAddString;
import it.unisa.ifttt_group_9.Action.ActionText;
import it.unisa.ifttt_group_9.Controller.PanelPopUPManager;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

public class ActionTextTest {

    @Test
    public void testExecuteAction() {
        // Create an instance of ActionText with message text and variable substitution enabled
        String messageText = "This is an example message from ActionText.";
        Boolean variableSubstitution = true;
        ActionText actionText = new ActionText(messageText, variableSubstitution);

        // Execute the ActionText method
        actionText.executeAction();

        // Check the output
        if (JOptionPane.OK_OPTION == new PanelPopUPManager("Action Text", messageText).showMessage()) {
            System.out.println("The alert was closed with OK.");
        } else {
            System.out.println("The alert was closed without pressing OK.");
        }
    }


}
