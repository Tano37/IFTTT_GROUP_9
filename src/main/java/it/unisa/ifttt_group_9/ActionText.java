package it.unisa.ifttt_group_9;

import it.unisa.ifttt_group_9.exceptions.IllegalMessageException;

public class ActionText implements Action{

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


    }
}
