package it.unisa.ifttt_group_9;

public class ActionText implements Action{

    private String text;

    public ActionText(String text) {

        this.text = text;
    }

    @Override
    public void executeAction(){
        //mostro la view ActionTextView quando viene eseguito il metodo
        //il controllore della View è il ActionTextViewController
        //il modello è questo file (ActionText)
        ActionTextView.show();


    }
}
