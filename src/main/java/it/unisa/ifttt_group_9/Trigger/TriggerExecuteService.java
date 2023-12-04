package it.unisa.ifttt_group_9.Trigger;

import javafx.concurrent.Service;
import javafx.concurrent.Task;

import java.io.IOException;
import java.util.Arrays;

public class TriggerExecuteService extends Service<Integer> {
    private TriggerExitStatus triggerSelected;

    public TriggerExecuteService(TriggerExitStatus triggerSelected) {
        this.triggerSelected = triggerSelected;
    }


    @Override
    protected Task<Integer> createTask() {
        return new Task<Integer>() {
            @Override
            protected Integer call() throws Exception {
                int exitStatus1;

                    ProcessBuilder processBuilder = new ProcessBuilder();
                    //System.out.println(triggerSelected.getStringPath()+ triggerSelected.getCommandLine());
                    // Aggiungere il percorso dell'eseguibile e gli eventuali argomenti
                    processBuilder.command(Arrays.asList(triggerSelected.getStringPath(), triggerSelected.getCommandLine()));

                    // Eseguire il processo
                    Process process = processBuilder.start();

                    // Attendere che il processo termini
                    //System.out.println(process.waitFor());


                return process.waitFor();
            }
        };
    }
}
