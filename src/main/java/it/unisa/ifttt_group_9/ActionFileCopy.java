package it.unisa.ifttt_group_9;

import javax.swing.*;
import java.io.IOException;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;


public class ActionFileCopy implements Action {

    String filePath;
    String destinationDirPath;

    public ActionFileCopy(String filePath, String destinationDirPath) {
        this.destinationDirPath = destinationDirPath;
        this.filePath = filePath;
    }

    @Override
    public void executeAction() {
        // Percorso del file da spostare
        Path sourcePath = Paths.get(filePath);

        // Percorso della directory di destinazione
        Path destinationPath = Paths.get(destinationDirPath);

        try {
            // Sposta il file nella nuova posizione
            Files.move(sourcePath, destinationPath.resolve(sourcePath.getFileName()), StandardCopyOption.REPLACE_EXISTING);


        } catch (IOException e) {
            System.err.println("Error during file coping " + e.getMessage());
        }
    }

}


