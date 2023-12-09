package it.unisa.ifttt_group_9.TriggerTest;

import it.unisa.ifttt_group_9.Trigger.TriggerExitStatus;
import javafx.application.Platform;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import it.unisa.ifttt_group_9.Controller.PrincipalStageViewController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

class TriggerExitStatusTest {

    /*This exevutable exactly accepts 2 arguments which will be printed on a file, if it receives a diverse number
    * of arguments the execution will fail*/
    private String executablePath=System.getProperty("user.dir") + "esemple.exe ";

    @Test
    void testEvaluate(){
            TriggerExitStatus trigger1 = new TriggerExitStatus(executablePath, "firstArgument, secondArgument", 0);
            TriggerExitStatus trigger2 = new TriggerExitStatus(executablePath, "firstArgument, secondArgument, thirdArgument", 1);
            TriggerExitStatus trigger3 = new TriggerExitStatus(executablePath, "firstArgument, secondArgument, thirdArgument", 0);

            //The service functionality will be emulated, in this class no javaFX toolkit is initialized
            try {
                ServiceEmulation(trigger1);
                ServiceEmulation(trigger2);
                ServiceEmulation(trigger3);
            } catch (IOException | InterruptedException e) {
                throw new RuntimeException(e);
            }

            assertEquals(trigger1.getExitExpected(), trigger1.getExitValue());

            assertEquals(trigger2.getExitExpected(), trigger2.getExitValue());

            assertNotEquals(trigger3.getExitExpected(), trigger3.getExitValue());
    }

    private void ServiceEmulation (TriggerExitStatus triggerSelected) throws IOException, InterruptedException {
        String[] elements = triggerSelected.getCommandLine().split(",");

        ArrayList<String> lineCommands = new ArrayList<>();
        for (String element : elements) {
            //Removing eventual spaces before and after the single commands
            String trimmedElement = element.trim();
            lineCommands.add(trimmedElement);
        }

        // Adding executable program path and related line commands to command
        List<String> command = new ArrayList<>();
        command.add(triggerSelected.getStringPath());
        command.addAll(lineCommands);

        //Building a process based on command
        ProcessBuilder processBuilder = new ProcessBuilder(command);

        //Starting the process and setting a trigger variable to its return value
        Process process = processBuilder.start();
        triggerSelected.setExitValue(process.waitFor());
    }
}
