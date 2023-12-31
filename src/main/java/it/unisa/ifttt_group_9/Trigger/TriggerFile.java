package it.unisa.ifttt_group_9.Trigger;

import java.io.File;

public class TriggerFile extends TriggerAbstractClass {
    private String directoryPath;
    private String fileName;

    public TriggerFile(String directoryPath, String fileName, boolean negate, Trigger trigger, boolean nextTriggerAndOr) {
        super(negate, trigger, nextTriggerAndOr);
        this.directoryPath = directoryPath;
        this.fileName = fileName;
    }
    public TriggerFile(String directoryPath, String fileName, boolean negate) {
        super(negate);
        this.directoryPath = directoryPath;
        this.fileName = fileName;

    }
    public TriggerFile(String directoryPath, String fileName) {
        this.directoryPath = directoryPath;
        this.fileName = fileName;
    }

    //Checks if the file  exists
    @Override
    public boolean evaluate() {
        File file = new File(directoryPath, fileName);
        if(precTriggerAndOr){
            return negate != file.exists() && super.evaluate();
        }else {
            return negate != file.exists() || super.evaluate();
        }

    }

    @Override
    public String toString() {
        return (negate?"!" : "") + "TriggerFile: " + fileName + " \n" +super.toString();
    }
}
