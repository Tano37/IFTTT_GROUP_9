package it.unisa.ifttt_group_9;

import javax.swing.*;
import java.io.IOException;
import java.io.*;


public class ActionFileCopy implements Action {

    String filePath;
    String destinationDirPath;

    public ActionFileCopy(String filePath, String destinationDirPath) {
        this.destinationDirPath = destinationDirPath;
        this.filePath = filePath;
    }

    @Override
    public void executeAction() {
        File sourceFile = new File(filePath);

        // Percorso della cartella di destinazione
        File destinationFolder = new File(destinationDirPath);

        try (InputStream inputStream = new FileInputStream(sourceFile);
             OutputStream outputStream = new FileOutputStream(new File(destinationFolder, sourceFile.getName()))) {

            byte[] buffer = new byte[1024];
            int length;

            // Legge i dati dal file di origine e li scrive nel file di destinazione
            while ((length = inputStream.read(buffer)) > 0) {
                outputStream.write(buffer, 0, length);
            }

            JOptionPane.showConfirmDialog(null, "The file has been copied", "FileCopy", JOptionPane.DEFAULT_OPTION);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}


