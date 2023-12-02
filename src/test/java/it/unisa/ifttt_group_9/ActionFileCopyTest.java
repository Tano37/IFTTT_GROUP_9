package it.unisa.ifttt_group_9;

import it.unisa.ifttt_group_9.Action.ActionFileCopy;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.nio.file.*;

import static org.junit.jupiter.api.Assertions.*;

public class ActionFileCopyTest {

    @Test
    public void testExecuteAction() throws IOException {
        // Crea una cartella temporanea per il test
        String tempDir = "tempTestDir";
        Files.createDirectory(Paths.get(tempDir));

        try {
            // Crea un file temporaneo da copiare
            String sourceFilePath = tempDir + "/source.txt";
            Files.write(Paths.get(sourceFilePath), "Hello, World!".getBytes());

            // Crea un'istanza di ActionFileCopy
            ActionFileCopy actionFileCopy = new ActionFileCopy(sourceFilePath, tempDir);

            // Esegui l'azione
            actionFileCopy.executeAction();

            // Verifica se il file è stato copiato correttamente
            String destinationFilePath = tempDir + "/source.txt";
            Path destinationPath = Paths.get(destinationFilePath);
            assertTrue(Files.exists(destinationPath), "Il file copiato non esiste");

            // Leggi il contenuto del file copiato e verifica se è corretto
            String content = new String(Files.readAllBytes(destinationPath));
            assertEquals("Hello, World!", content, "Il contenuto del file copiato non è corretto");

        } finally {
            // Elimina la cartella temporanea e i file creati durante il test
            deleteDirectory(tempDir);
        }
    }

    // Metodo per eliminare una cartella e i suoi contenuti ricorsivamente
    private static void deleteDirectory(String directoryPath) throws IOException {
        Path directory = Paths.get(directoryPath);
        Files.walk(directory)
                .sorted((a, b) -> b.compareTo(a))
                .forEach(file -> {
                    try {
                        Files.delete(file);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
    }
}
