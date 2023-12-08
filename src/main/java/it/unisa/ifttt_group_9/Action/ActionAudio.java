package it.unisa.ifttt_group_9.Action;

import it.unisa.ifttt_group_9.Controller.PanelPopUPManager;

import javax.sound.sampled.*;
import javax.swing.*;
import java.io.File;
import java.io.IOException;

public class ActionAudio extends ActionDecorator {
    private String filePath;
    private volatile boolean continuePlaying = true;

    public ActionAudio(String filePath, Action action) {
        super(action);
        this.filePath = filePath;
    }
    public ActionAudio(String filePath) {
        super(null);
        this.filePath = filePath;
    }

    @Override
    public void executeAction() {
        //super.executeAction in the function playAudio...
        playAudioWithPopup(filePath);
    }

    public String getFilePath() {
        return filePath;
    }

    public void playAudioWithPopup(String filePath) {
        // Ottieni il nome del file dal percorso
        String[] directories = filePath.split("/");
        String name = directories[directories.length - 1];

        // Avvia un thread per la riproduzione audio
        new Thread(() -> playAudio(filePath)).start();
        super.executeAction();
        // Mostra il dialogo pop-up
        int scelta = new PanelPopUPManager("AudioAction", name).showMessage();

        // Verifica se l'utente ha premuto "OK"
        if (scelta == JOptionPane.OK_OPTION) {
            System.out.println("You pressed OK. Closing the alert...");
        } else {
            System.out.println("Alert closed without pressing OK.");
        }

        // Imposta la variabile di stato per terminare la riproduzione
        continuePlaying = false;
    }

    public void playAudio(String filePath) {
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

   /* public static void main(String[] args) {

        ActionAudio audio = new ActionAudio("C:/Users/giuse/Downloads/ProvaAudio.wav");
        File file = new File(audio.getFilePath());

        System.out.println(file.getPath());
        audio.executeAction();

    }*/

    @Override
    public String toString() {
        return "{ActionAudio: " + filePath + " }\n" + super.toString();
    }
}
