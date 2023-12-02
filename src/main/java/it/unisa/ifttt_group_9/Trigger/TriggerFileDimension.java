package it.unisa.ifttt_group_9.Trigger;

import java.io.File;

public class TriggerFileDimension implements Trigger {

    private String filePath;
    private long maxSize;

    @Override
    public boolean evaluate() {

        // Create a File object
        File file = new File(filePath);

        // Check if the file exists
        if (file.exists()) {
            // Get the file size in bytes
            long fileSize = file.length();

            return fileSize > maxSize;

        } else {
            System.out.println(filePath + " doesn't exists");
            return false;
        }
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public void setMaxSize(long maxSize) {
        this.maxSize = maxSize;
    }
}
