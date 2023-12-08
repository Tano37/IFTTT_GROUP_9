package it.unisa.ifttt_group_9;

import it.unisa.ifttt_group_9.Action.ActionAudio;
import org.junit.Test;

public class ActionAudioTest {

    @Test
    public void testExecuteAction() {
        // Creazione di un'istanza di ActionAudio con un percorso audio di esempio
        ActionAudio audio = new ActionAudio("src/main/resources/it/unisa/ifttt_group_9/testAudio.wav");

        // Eseguire l'azione audio
        audio.executeAction();

        // Manuale: Controlla la console per la verifica manuale dell'output audio
        // Inoltre, puoi verificare manualmente l'output della console per le stampe di OK o senza OK.

        // Dato che l'audio viene eseguito in un thread separato, potrebbe essere necessario attendere un po' prima di chiudere il test.
        try {
            Thread.sleep(10000); // Attendere 10 secondi (10.000 millisecondi) per l'esecuzione dell'audio
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void testPlayAudio() {
        // Creazione di un'istanza di ActionAudio con un percorso audio di esempio
        ActionAudio audio = new ActionAudio("src/main/resources/it/unisa/ifttt_group_9/testAudio.wav");

        // Eseguire il metodo playAudio
        audio.playAudio(audio.getFilePath());

        // Manuale: Controlla la console per la verifica manuale dell'output audio
        // Puoi regolare il tempo di attesa o aggiungere un'assertion per verificare che l'audio sia stato eseguito correttamente
    }

    @Test
    public void testPlayAudioWithPopup() {
        // Creazione di un'istanza di ActionAudio con un percorso audio di esempio
        ActionAudio audio = new ActionAudio("src/main/resources/it/unisa/ifttt_group_9/testAudio.wav");

        // Eseguire il metodo playAudioWithPopup
        audio.playAudioWithPopup(audio.getFilePath());

        // Manuale: Controlla la console per la verifica manuale dell'output audio e la comparsa del popup
        // Puoi regolare il tempo di attesa o aggiungere un'assertion per verificare che l'audio sia stato eseguito correttamente e che il popup sia apparso
    }

    // Puoi aggiungere altri test secondo le tue esigenze
}

    // Puoi aggiungere altri test secondo le tue esigenze

