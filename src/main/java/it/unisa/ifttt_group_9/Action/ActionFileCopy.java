package it.unisa.ifttt_group_9.Action;

import it.unisa.ifttt_group_9.Action.Action;

import javax.swing.*;
import java.io.IOException;
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
            JOptionPane.showConfirmDialog(null, "The file has been copied", "FileCopy", JOptionPane.DEFAULT_OPTION);

        } catch (IOException e) {
            System.err.println("Error during file coping " + e.getMessage());
            JOptionPane.showConfirmDialog(null, "The file HASN'T been copied", "FileCopy", JOptionPane.DEFAULT_OPTION);
        }
    }

}


