package it.unisa.ifttt_group_9.TriggerTest;

import it.unisa.ifttt_group_9.Trigger.TriggerFileDimension;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TriggerFileDimensionTest {
    //Dim = 10866344 Byte
    String filePathTest = System.getProperty("user.dir") + "\\src\\test\\java\\it\\unisa\\ifttt_group_9\\";


    @Test
    public void testFileLargerThanMaxSize() {
        // Create an instance of TriggerFileDimension
        TriggerFileDimension trigger = new TriggerFileDimension(filePathTest, 1024);

        // Evaluate the trigger
        boolean result = trigger.evaluate();

        // The file is larger than the specified maximum size, so the result should be true
        assertTrue(result);
    }

    @Test
    public void testFileSmallerOrEqualToMaxSize() {
        // Create an instance of TriggerFileDimension
        TriggerFileDimension trigger = new TriggerFileDimension(filePathTest, 10866344 + 1);


        // Evaluate the trigger
        boolean result = trigger.evaluate();

        // The file is smaller or equal to the specified maximum size, so the result should be false
        assertFalse(result);
    }

    @Test
    public void testNonExistingFile() {
        // Create an instance of TriggerFileDimension
        TriggerFileDimension trigger = new TriggerFileDimension("NonExistingFilePath", 1024);

        // Evaluate the trigger
        boolean result = trigger.evaluate();

        // The file does not exist, so the result should be false
        assertFalse(result);
    }
}
