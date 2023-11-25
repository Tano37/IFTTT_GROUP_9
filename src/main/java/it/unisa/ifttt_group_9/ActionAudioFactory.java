package it.unisa.ifttt_group_9;

public class ActionAudioFactory implements ActionFactory{

    @Override
    public Action createAction(String text) {
        return new ActionAudio(text);
    }
}
