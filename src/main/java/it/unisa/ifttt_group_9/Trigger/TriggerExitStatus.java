package it.unisa.ifttt_group_9.Trigger;

import java.util.concurrent.atomic.AtomicBoolean;

public class TriggerExitStatus implements Trigger {
    private String stringPath;
    private String commandLine;
    private int exitStatus;

    public TriggerExitStatus(String stringPath, String commandLine, int exitStatus) {
        this.stringPath = stringPath;
        this.commandLine = commandLine;
        this.exitStatus = exitStatus;
    }


    public String getStringPath() {
        return stringPath;
    }

    public String getCommandLine() {
        return commandLine;
    }

    public int getExitStatus() {
        return exitStatus;
    }

    @Override
    public boolean evaluate() {


        TriggerExecuteService myTrigger = new TriggerExecuteService(this);
        myTrigger.setOnSucceeded(e->{
                myTrigger.getValue();
                });
        myTrigger.start();

        //return exitStatus1 == this.exitStatus;
        return false;

    }
}
