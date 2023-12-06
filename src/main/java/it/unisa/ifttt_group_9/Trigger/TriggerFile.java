package it.unisa.ifttt_group_9.Trigger;

import java.io.File;

public class TriggerFile extends AbstractTriggerDecorator {
    private String directoryPath;
    private String fileName;
    private boolean negate;

    public TriggerFile(String directoryPath, String fileName, boolean negate) {
        super(negate);
        this.directoryPath = directoryPath;
        this.fileName = fileName;
        this.negate=false;
    }
    public TriggerFile(String directoryPath, String fileName) {
        super(false);
        this.directoryPath = directoryPath;
        this.fileName = fileName;
        this.negate=false;
    }

    @Override
    public boolean evaluate() {
        File file = new File(directoryPath, fileName);
        return negate != file.exists();
    }
}
