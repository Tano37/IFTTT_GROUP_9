package it.unisa.ifttt_group_9;

import it.unisa.ifttt_group_9.Trigger.Trigger;
import it.unisa.ifttt_group_9.Trigger.TriggerFile;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

class TriggerFileTest {

    @Test
    public void testEvaluate(){
        String directoryPath=System.getProperty("user.dir") + "\\src\\test\\java\\it\\unisa\\ifttt_group_9\\";
        String fileName="testfile.txt";
        File file1 = new File(directoryPath, fileName);
        Trigger trigger = new TriggerFile(directoryPath, fileName);

        assertEquals(trigger.evaluate(), file1.exists());

        File file2= new File(directoryPath, "unexistingFile.txt");
        Trigger trigger2 = new TriggerFile(directoryPath, "unexistingFile.txt");
        assertEquals(trigger2.evaluate(), file2.exists());
    }


}