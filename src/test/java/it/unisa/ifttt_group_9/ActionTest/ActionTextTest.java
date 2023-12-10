package it.unisa.ifttt_group_9.ActionTest;

import it.unisa.ifttt_group_9.Action.ActionFileAddString;
import it.unisa.ifttt_group_9.Action.ActionText;
import it.unisa.ifttt_group_9.Controller.PanelPopUPManager;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import static org.junit.jupiter.api.Assertions.*;


public class ActionTextTest {

        @Test
        public void testExecuteActionWithMessage() {
            String messageText = "This is an example message from ActionText";
            Boolean variableSubstitution = true;
            ActionText actionText = new ActionText(messageText, variableSubstitution);

            // Execute the ActionText method
            actionText.executeAction();

            // Verify that the message text was displayed correctly
            assertEquals("This is an example message from ActionText", actionText.getText());
            assertEquals(true, actionText.getVariableSubstitution());
        }


}
