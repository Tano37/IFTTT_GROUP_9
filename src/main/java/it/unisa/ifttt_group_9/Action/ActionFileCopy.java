package it.unisa.ifttt_group_9.Action;

import it.unisa.ifttt_group_9.Controller.PanelPopUPManager;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;


public class ActionFileCopy extends ActionAbstractClass {

    private String filePath;
    private String destinationDirPath;

    public ActionFileCopy(String filePath, String destinationDirPath, Action action) {
        super(action);
        this.destinationDirPath = destinationDirPath;
        this.filePath = filePath;
    }
    public ActionFileCopy(String filePath, String destinationDirPath) {
        super(null);
        this.destinationDirPath = destinationDirPath;
        this.filePath = filePath;
    }

    /*Defines the path to the source file (sourcePath) and the path to the destination directory (destinationPath).
     Attempts to move the file specified by filePath to the destination directory.
     If successful, it displays a pop-up message indicating that the file has been copied.
     If an IOException occurs while copying the file, it catches the exception, prints an error message, and displays a pop-up message.
     In the final clause, the super function is called for any related actions after*/

    @Override
    public void executeAction() {
        // Path of the file to be moved
        Path sourcePath = Paths.get(filePath);

        // Path to the destination directory
        Path destinationPath = Paths.get(destinationDirPath);

        try {
            // Move the file to the new location
            Files.move(sourcePath, destinationPath.resolve(sourcePath.getFileName()), StandardCopyOption.REPLACE_EXISTING);
            new PanelPopUPManager(this.getClass().getName(),"The file has been copied");
        } catch (IOException e) {
            System.err.println("Error during file coping " + e.getMessage());
            new PanelPopUPManager(this.getClass().getName(),"The file HASN'T been copied");
        } finally {
            super.executeAction();
        }
    }

    @Override
    public String toString() {
        return "{ActionFileCopy: " + filePath + " }\n" + super.toString();
    }
}


