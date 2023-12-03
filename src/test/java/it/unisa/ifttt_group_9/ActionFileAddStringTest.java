
package it.unisa.ifttt_group_9;
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
        ActionFileAddString action = new ActionFileAddString(testFilePath, testStringToAdd);

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
                //System.out.println("ciaooo");
            } catch (IOException e) {
                e.printStackTrace(); // Handle or log the exception accordingly
            }
        }
    }




}

