package it.unisa.ifttt_group_9.Action;

import it.unisa.ifttt_group_9.Controller.PanelPopUPManager;
import it.unisa.ifttt_group_9.CounterManager;
import it.unisa.ifttt_group_9.Controller.PanelPopUPManager;

import it.unisa.ifttt_group_9.exceptions.IllegalMessageException;

import javax.swing.*;

public class ActionText extends ActionDecorator {

    private String text;
    private Boolean variableSubstitution;

    public ActionText(String text, Action action, Boolean variableSubstitution) throws IllegalMessageException {
        super(action);
        if (text == null || text.trim().isEmpty()) {
            throw new IllegalMessageException("Il testo dell'azione non può essere nullo o vuoto");
        }
        this.text = text;
        this.variableSubstitution=variableSubstitution;
    }

    public ActionText(String text, Boolean variableSubstitution) {
        super(null);
        this.text = text;
        this.variableSubstitution=variableSubstitution;
    }


    @Override
    public String toString() {
        return "ActionText{" +
                "text='" + text + '\'' +
                ", variableSubstitution=" + variableSubstitution +
                "\n" + tempAction +
                '}';
    }

    public String getText() {
        return text;
    }

    @Override
    public void executeAction() {

        // Mostra un dialogo con un messaggio e un pulsante "OK"


        if (this.variableSubstitution)
            this.text= CounterManager.counterSubstitution(this.text);
        // Verifica se l'utente ha premuto "OK"
        int scelta = new PanelPopUPManager("Action Text", text).showMessage();

        super.executeAction();

        if (scelta == JOptionPane.OK_OPTION) {
            System.out.println("You pressed OK. Closing the alert...");
        } else {
            System.out.println("Allert closed without pressing OK.");
        }



    }
}
