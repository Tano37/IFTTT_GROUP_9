package it.unisa.ifttt_group_9.Trigger;

import java.util.concurrent.atomic.AtomicBoolean;

public class TriggerExitStatus extends TriggerDecorator {
    private String stringPath;
    private String commandLine;
    private int exitExpected;
    private int exitValue; // Variabile di istanza per memorizzare il valore restituito

    public TriggerExitStatus(String stringPath, String commandLine, int exitStatus, boolean negate, Trigger trigger, boolean nextTriggerAndOr) {
        super(negate, trigger, nextTriggerAndOr);
        this.stringPath = stringPath;
        this.commandLine = commandLine;
        this.exitExpected = exitStatus;
    }
    public TriggerExitStatus(String stringPath, String commandLine, int exitStatus, boolean negate) {
        super(negate);
        this.stringPath = stringPath;
        this.commandLine = commandLine;
        this.exitExpected = exitStatus;
    }
    public TriggerExitStatus(String stringPath, String commandLine, int exitStatus) {
        this.stringPath = stringPath;
        this.commandLine = commandLine;
        this.exitExpected = exitStatus;
    }


    public void setExitValue(int exitValue) {
        this.exitValue = exitValue;
    }

    public String getStringPath() {
        return stringPath;
    }

    public String getCommandLine() {
        return commandLine;
    }

    public int getExitExpected() {
        return exitExpected;
    }

    public int getExitValue() {
        return exitValue;
    }

    @Override
    public boolean evaluate() {
        AtomicBoolean evaluationResult = new AtomicBoolean(false);

        TriggerExecuteService myTrigger = new TriggerExecuteService(this);
        myTrigger.setOnSucceeded(e -> {

            //System.out.println("Risultato esecuzione: "+ this.getExitValue()+"risultato confrontato="+ this.getExitExpected());

        });
        myTrigger.start();

        if(precTriggerAndOr){
            return (negate == (this.getExitExpected() != this.getExitValue())) && super.evaluate();
        }else {
            return (negate == (this.getExitExpected() != this.getExitValue())) || super.evaluate();

        }

    }

    @Override
    public String toString() {
        return (negate?"!" : "") + "{TriggerExitStatus: " + exitExpected + " }\n"  + super.toString();
    }
}
