package it.unisa.ifttt_group_9;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TriggerFileDimensionTest {
    //Dim = 10866344 Byte
    String filePathTest =  "C:\\Users\\alfon\\OneDrive\\Desktop\\Università Magistrale\\Software Engineering\\IFTTT_GROUP_9\\src\\main\\resources\\it\\unisa\\ifttt_group_9\\draw.exe";

    @Test
    public void testFileLargerThanMaxSize() {
        // Create an instance of TriggerFileDimension
        TriggerFileDimension trigger = new TriggerFileDimension();

        // Set the file path and maximum size for testing
        trigger.filePath = filePathTest;
        trigger.maxSize = 1024; // for example, 1 KB

        // Evaluate the trigger
        boolean result = trigger.evaluate();

        // The file is larger than the specified maximum size, so the result should be true
        assertTrue(result);
    }

    @Test
    public void testFileSmallerOrEqualToMaxSize() {
        // Create an instance of TriggerFileDimension
        TriggerFileDimension trigger = new TriggerFileDimension();

        // Set the file path and maximum size for testing
        trigger.filePath = filePathTest;
        trigger.maxSize = 10866346; // for example, 1 KB

        // Evaluate the trigger
        boolean result = trigger.evaluate();

        // The file is smaller or equal to the specified maximum size, so the result should be false
        assertFalse(result);
    }

    @Test
    public void testNonExistingFile() {
        // Create an instance of TriggerFileDimension
        TriggerFileDimension trigger = new TriggerFileDimension();

        // Set a non-existing file path for testing
        trigger.filePath = "nonExistingPath";

        // Evaluate the trigger
        boolean result = trigger.evaluate();

        // The file does not exist, so the result should be false
        assertFalse(result);
    }
}
