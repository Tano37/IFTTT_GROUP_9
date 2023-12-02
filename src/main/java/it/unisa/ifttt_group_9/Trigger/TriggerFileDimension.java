package it.unisa.ifttt_group_9.Trigger;

import java.io.File;

public class TriggerFileDimension implements Trigger {

    private String filePath;
    private long maxSize;


    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public long getMaxSize() {
        return maxSize;
    }

    public void setMaxSize(long maxSize) {
        this.maxSize = maxSize;
    }

    private long maxSize;

    public TriggerFileDimension(String filePath, long maxSize) {
        this.filePath = filePath;
        this.maxSize = maxSize;
    }

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
