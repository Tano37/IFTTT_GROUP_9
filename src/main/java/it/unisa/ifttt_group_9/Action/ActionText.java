package it.unisa.ifttt_group_9.Action;

import it.unisa.ifttt_group_9.Controller.PanelPopUPManager;
import it.unisa.ifttt_group_9.exceptions.IllegalMessageException;

import javax.swing.*;

public class ActionText implements Action {

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

        PanelPopUPManager panelPopUPManager = new PanelPopUPManager("ActionText", text);
        int scelta = panelPopUPManager.showMessage();
        // Verifica se l'utente ha premuto "OK"
       if (scelta == JOptionPane.OK_OPTION) {
            System.out.println("You pressed OK. Closing the alert...");
        } else {
            System.out.println("Allert closed without pressing OK.");
        }
    }
}
