package it.unisa.ifttt_group_9.factories;

import it.unisa.ifttt_group_9.Action;
import it.unisa.ifttt_group_9.ActionAudio;

public class AudioActionFactory implements ActionFactory{

    private String filePath;
    private volatile boolean continuePlaying = true;

    public AudioActionFactory(String filePath, boolean continuePlaying) {
        this.filePath = filePath;
        this.continuePlaying = continuePlaying;
    }


    @Override
    public Action createAction() {
        return new ActionAudio(this.filePath, this.continuePlaying);
    }
}
