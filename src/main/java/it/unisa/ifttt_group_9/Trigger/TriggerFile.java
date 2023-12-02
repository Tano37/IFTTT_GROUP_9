package it.unisa.ifttt_group_9.Trigger;

import java.io.File;

public class TriggerFile implements Trigger {
    private String directoryPath;
    private String fileName;

    public TriggerFile(String directoryPath, String fileName) {
        this.directoryPath = directoryPath;
        this.fileName = fileName;
    }

    @Override
    public boolean evaluate() {
        File file = new File(directoryPath, fileName);
        return file.exists();
    }
}
