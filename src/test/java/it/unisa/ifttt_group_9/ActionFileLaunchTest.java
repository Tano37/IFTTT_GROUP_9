package it.unisa.ifttt_group_9;

import it.unisa.ifttt_group_9.Action.ActionFileLaunch;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

public class ActionFileLaunchTest {

    @Test
    public void testExecuteAction() {
        // Replace these values with the actual path to your executable and its arguments
        String executablePath =System.getProperty("user.dir") + "\"src\\main\\resources\\it\\unisa\\ifttt_group_9\\draw.exe\"";
        String arguments = "";

        // Create an instance of ActionFileLaunch
        ActionFileLaunch actionFileLaunch = new ActionFileLaunch(executablePath, arguments);

        // Execute the action
        try {
            actionFileLaunch.executeAction();

            // Since launching a process is environment-dependent, we can't assert much about it.
            // You might want to add additional checks based on your specific use case.

            // For now, we'll assume that if no exceptions are thrown during execution, the test is successful.
            assertTrue(true);

        } catch (Exception e) {
            // If an exception is thrown during execution, consider the test as failed.
            fail("Exception during execution: " + e.getMessage());
        }
    }
}
