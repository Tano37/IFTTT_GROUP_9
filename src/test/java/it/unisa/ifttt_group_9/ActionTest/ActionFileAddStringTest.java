
package it.unisa.ifttt_group_9.ActionTest;
import it.unisa.ifttt_group_9.Action.ActionFileAddString;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

class ActionFileAddStringTest {

    @Test
    void executeAction_SuccessfullyAddsStringToFile() {
        // Arrange
        String testFilePath = "testFile.txt";
        String testStringToAdd = "TestString";
        Boolean variabileSubstitution = false;
        ActionFileAddString action = new ActionFileAddString(testFilePath, testStringToAdd, variabileSubstitution);

        // Act
        action.executeAction();

        // Assert
        try {
            String content = Files.readString(Paths.get(testFilePath));
            assertTrue(content.contains(testStringToAdd));
        } catch (IOException e) {
            fail("Failed to read file content: " + e.getMessage());
        } finally {
            // Clean up: Delete the test file
            try {
                Files.deleteIfExists(Paths.get(testFilePath));
            } catch (IOException e) {
                e.printStackTrace(); // Handle or log the exception accordingly
            }
        }
    }




}

