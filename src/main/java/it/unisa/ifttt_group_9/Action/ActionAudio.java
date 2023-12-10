package it.unisa.ifttt_group_9.Action;

import it.unisa.ifttt_group_9.Controller.PanelPopUPManager;

import javax.sound.sampled.*;
import javax.swing.*;
import java.io.File;
import java.io.IOException;

public class ActionAudio extends ActionAbstractClass {
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

    /*This method plays an audio file in a separate thread, displays a pop-up dialog
    box with information about the audio action, and finally checks for user interaction
    and stops playback accordingly.*/

    public void playAudioWithPopup(String filePath) {
        // Get the file name from the path
        String[] directories = filePath.split("/");
        String name = directories[directories.length - 1];

        // Start a thread for audio playback
        new Thread(() -> playAudio(filePath)).start();
        super.executeAction();
        // Show pop-up dialog
        int scelta = new PanelPopUPManager("AudioAction", name).showMessage();

        // Set the status variable to end playback
        continuePlaying = false;
    }

    /*This method handles the playback of an audio file using the Java Sound
    API Reads the audio data and writes it to the output line.
    It allows controlled interruption of playback using the continuePlaying variable.*/
    public void playAudio(String filePath) {
        try {
            // Get an AudioInputStream from the audio file
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(filePath));

            // Get an AudioInputStream from the audio file
            AudioFormat format = audioInputStream.getFormat();

            // Creates a DataLine.Info to get a SourceDataLine, which is used for playback
            DataLine.Info info = new DataLine.Info(SourceDataLine.class, format);

            // Get a SourceDataLine
            SourceDataLine line = (SourceDataLine) AudioSystem.getLine(info);

            // Open the line for playback
            line.open(format);

            // Start playback
            line.start();

            // Read the audio data from the file and send it to the line
            byte[] buffer = new byte[4096];
            int bytesRead = 0;

            while (continuePlaying && (bytesRead = audioInputStream.read(buffer)) != -1) {
                line.write(buffer, 0, bytesRead);
            }

            // Close the AudioInputStream and the line after playback
            audioInputStream.close();
            line.drain();
            line.close();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }


    @Override
    public String toString() {

        return "{ActionAudio: " + filePath + " }\n" + super.toString();
    }
}
