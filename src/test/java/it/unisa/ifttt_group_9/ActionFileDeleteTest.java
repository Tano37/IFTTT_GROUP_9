package it.unisa.ifttt_group_9;

import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.nio.file.*;

import static org.junit.jupiter.api.Assertions.*;

public class ActionFileDeleteTest {

    @Test
    public void testExecuteAction() throws IOException {
        // Create a temporary file for the test

        Path tempFilePath = Paths.get("tempTestFile.txt");
        Files.write(tempFilePath, "Hello, World!".getBytes());

        // Create an instance of ActionFileDelete
        ActionFileDelete actionFileDelete = new ActionFileDelete(tempFilePath.toString());

        // Execute the action
        actionFileDelete.executeAction();

        // Verify that the file is deleted
        assertFalse(Files.exists(tempFilePath), "The file was not deleted");

        // Clean up: Delete the temporary file if not deleted during the action execution
        Files.deleteIfExists(tempFilePath);
    }
}
