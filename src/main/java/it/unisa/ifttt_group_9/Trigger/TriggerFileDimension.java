package it.unisa.ifttt_group_9.Trigger;

import java.io.File;

public class TriggerFileDimension extends TriggerAbstractClass {

    private String filePath;
    private long maxSize;

    public TriggerFileDimension(String filePath, long maxSize, boolean negate, Trigger trigger, boolean precTriggerAndOr) {
        super(negate, trigger, precTriggerAndOr);
        this.filePath = filePath;
        this.maxSize = maxSize;
    }


    public TriggerFileDimension(String filePath, long maxSize, boolean negate) {
        super(negate);
        this.filePath = filePath;
        this.maxSize = maxSize;
    }

    public TriggerFileDimension(String filePath, long maxSize) {
        this.filePath = filePath;
        this.maxSize = maxSize;
    }


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




    //checks if the file dimension exceed the setted one
    @Override
    public boolean evaluate() {

        // Create a File object
        File file = new File(filePath);

        // Check if the file exists
        if (file.exists()) {
            // Get the file size in bytes
            long fileSize = file.length();

            if(precTriggerAndOr){
                return negate != fileSize > maxSize && super.evaluate();
            }else{
                return negate != fileSize > maxSize || super.evaluate();
            }



        } else {
            System.out.println(filePath + " doesn't exists");
            if(precTriggerAndOr){
                return negate && super.evaluate();
            }else{
                return negate || super.evaluate();
            }
        }
    }

    @Override
    public String toString() {
        return (negate?"!" : "") + "{TriggerFileDimension: " + maxSize + " }\n" +super.toString();
    }
}
