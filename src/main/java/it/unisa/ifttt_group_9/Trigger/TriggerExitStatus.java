package it.unisa.ifttt_group_9.Trigger;

import java.io.IOException;
import java.util.Arrays;

public class TriggerExitStatus implements Trigger {
    private String stringPath;
    private String commandLine;
    private int exitStatus;

    public TriggerExitStatus(String stringPath, String commandLine, int exitStatus) {
        this.stringPath = stringPath;
        this.commandLine = commandLine;
        this.exitStatus = exitStatus;
    }

    @Override
    public boolean evaluate() {
        try {
            // Creare il processo usando ProcessBuilder
            ProcessBuilder processBuilder = new ProcessBuilder();

            // Aggiungere il percorso dell'eseguibile e gli eventuali argomenti
            processBuilder.command(Arrays.asList(this.stringPath, this.commandLine));

            // Eseguire il processo
            Process process = processBuilder.start();

            // Attendere che il processo termini
            int exitStatus1 = process.waitFor();

            // Controllare se l'exit status è uguale al valore atteso
            return exitStatus1 == this.exitStatus;

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            System.out.println("Il programma ha lanciato un  eccezione; non può essere eseguito!");
            return false;
        }

    }
}
