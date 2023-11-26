package it.unisa.ifttt_group_9;

import it.unisa.ifttt_group_9.exceptions.IllegalMessageException;

import javax.swing.*;
import java.io.Serializable;

public class ActionText implements Action, Serializable {

    private String text;

    public ActionText(String text) throws IllegalMessageException {
        if (text == null || text.trim().isEmpty()) {
            throw new IllegalMessageException("Il testo dell'azione non può essere nullo o vuoto");
        }
        this.text = text;
    }

    @Override
    public String toString() {
        return "ActionText{" +
                "text='" + text + '\'' +
                '}';
    }

    public String getText() {
        return text;
    }

    @Override
    public void executeAction() {
       // Mostra un dialogo con un messaggio e un pulsante "OK"
        int scelta = JOptionPane.showConfirmDialog(null, text, "Action", JOptionPane.DEFAULT_OPTION);

        // Verifica se l'utente ha premuto "OK"
       if (scelta == JOptionPane.OK_OPTION) {
            System.out.println("You pressed OK. Closing the alert...");
        } else {
            System.out.println("Allert closed without pressing OK.");
        }
    }
}
