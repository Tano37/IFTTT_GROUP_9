package it.unisa.ifttt_group_9;

import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

class TriggerFileTest {

    @Test
    public void testEvaluate(){
        String directoryPath="C:/Users/davide/Documents/GitHub/IFTTT_GROUP_9/src/test/java/it/unisa/ifttt_group_9";
        String fileName="testfile.txt";
        File file1 = new File(directoryPath, fileName);
        File file2= new File(directoryPath, "unexistingFile.txt");
        assertTrue(file1.exists());
        assertFalse(file2.exists());
    }


}