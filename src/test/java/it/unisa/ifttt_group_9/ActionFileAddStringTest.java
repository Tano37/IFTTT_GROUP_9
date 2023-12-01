
package it.unisa.ifttt_group_9;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
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
                System.out.println("ciaooo");
            } catch (IOException e) {
                e.printStackTrace(); // Handle or log the exception accordingly
            }
        }
    }

    /*@Test
    void executeAction_ErrorHandling() {
        // Arrange
        String testFilePath = "nonexistentFolder/nonexistentFile.txt";
        String testStringToAdd = "TestString";
        ActionFileAddString action = new ActionFileAddString(testFilePath, testStringToAdd);

        // Act and Assert
        assertThrows(IOException.class, action::executeAction);
    }*/
}

