package it.unisa.ifttt_group_9;

import javax.sound.sampled.*;
import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;

public class ActionAudio implements Action, Serializable {
    private String filePath;
    private volatile boolean continuePlaying = true;

    public ActionAudio(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public void executeAction() {
        playAudioWithPopup(filePath);
    }

    public String getFilePath() {
        return filePath;
    }

    private void playAudioWithPopup(String filePath) {
        // Ottieni il nome del file dal percorso
        String[] directories = filePath.split("/");
        String name = directories[directories.length - 1];

        // Avvia un thread per la riproduzione audio
        new Thread(() -> playAudio(filePath)).start();

        // Mostra il dialogo pop-up
        int scelta = JOptionPane.showConfirmDialog(null, name, "Action", JOptionPane.DEFAULT_OPTION);

        // Verifica se l'utente ha premuto "OK"
        if (scelta == JOptionPane.OK_OPTION) {
            System.out.println("Hai premuto OK. Chiudendo l'allerta...");
        } else {
            System.out.println("Allerta chiusa senza premere OK.");
        }

        // Imposta la variabile di stato per terminare la riproduzione
        continuePlaying = false;
    }

    private void playAudio(String filePath) {
        try {
            // Ottieni un AudioInputStream dal file audio
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(filePath));

            // Ottieni il formato dell'audio
            AudioFormat format = audioInputStream.getFormat();

            // Crea un DataLine.Info per ottenere un SourceDataLine, che è utilizzato per la riproduzione
            DataLine.Info info = new DataLine.Info(SourceDataLine.class, format);

            // Ottieni un SourceDataLine
            SourceDataLine line = (SourceDataLine) AudioSystem.getLine(info);

            // Apri la linea per la riproduzione
            line.open(format);

            // Avvia la riproduzione
            line.start();

            // Leggi i dati audio dal file e inviali alla linea
            byte[] buffer = new byte[4096];
            int bytesRead = 0;

            while (continuePlaying && (bytesRead = audioInputStream.read(buffer)) != -1) {
                line.write(buffer, 0, bytesRead);
            }

            // Chiudi l'AudioInputStream e la linea dopo la riproduzione
            audioInputStream.close();
            line.drain();
            line.close();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

        ActionAudio audio = new ActionAudio("C:/Users/giuse/Downloads/ProvaAudio.wav");
        File file = new File(audio.getFilePath());

        System.out.println(file.getPath());
        audio.executeAction();

    }
}
