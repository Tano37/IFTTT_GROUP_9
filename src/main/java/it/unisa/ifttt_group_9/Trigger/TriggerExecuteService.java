package it.unisa.ifttt_group_9.Trigger;

import javafx.concurrent.Service;
import javafx.concurrent.Task;

import java.util.Arrays;

public class TriggerExecuteService extends Service<Void> {
    private TriggerExitStatus triggerSelected;

    public TriggerExecuteService(TriggerExitStatus triggerSelected) {
        this.triggerSelected = triggerSelected;
    }


    @Override
    protected Task<Void> createTask() {
        return new Task<>() {
            @Override
            protected Void call() throws Exception {
                int exitStatus1;

                    ProcessBuilder processBuilder = new ProcessBuilder();
                    //System.out.println(triggerSelected.getStringPath()+ triggerSelected.getCommandLine());
                    // Aggiungere il percorso dell'eseguibile e gli eventuali argomenti
                    processBuilder.command(Arrays.asList(triggerSelected.getStringPath(), triggerSelected.getCommandLine()));

                    // Eseguire il processo
                    Process process = processBuilder.start();
                    triggerSelected.setExitValue(process.waitFor());


                return null;
            }
        };
    }
}
