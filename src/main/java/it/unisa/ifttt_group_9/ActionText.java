package it.unisa.ifttt_group_9;

public class ActionText implements Action{

    private String text;

    public ActionText(String text) {

        this.text = text;
    }

    @Override
    public String toString() {
        return "ActionText{" +
                "text='" + text + '\'' +
                '}';
    }

    @Override
    public void executeAction() {


    }
}
