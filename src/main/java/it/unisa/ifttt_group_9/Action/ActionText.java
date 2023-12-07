package it.unisa.ifttt_group_9.Action;

import it.unisa.ifttt_group_9.Controller.PanelPopUPManager;
import it.unisa.ifttt_group_9.PrincipalStageMain;
import it.unisa.ifttt_group_9.exceptions.IllegalMessageException;

import javax.swing.*;

public class ActionText extends ActionDecorator {

    private String text;

    public ActionText(String text, Action action) throws IllegalMessageException {
        super(action);
        if (text == null || text.trim().isEmpty()) {
            throw new IllegalMessageException("Il testo dell'azione non può essere nullo o vuoto");
        }
        this.text = text;
    }

    public ActionText(String text) {
        super(null);
        this.text = text;
    }


    @Override
    public String toString() {
        return "ActionText{" +
                "text='" + text + '\'' +
                '}' + super.toString();
    }

    public String getText() {
        return text;
    }

    @Override
    public void executeAction() {
        // Mostra un dialogo con un messaggio e un pulsante "OK"

        int scelta = new PanelPopUPManager("Action Text", text).showMessage();
        super.executeAction();
        // Verifica se l'utente ha premuto "OK"
        if (scelta == JOptionPane.OK_OPTION) {
            System.out.println("You pressed OK. Closing the alert...");
        } else {
            System.out.println("Allert closed without pressing OK.");
        }


    }
}
