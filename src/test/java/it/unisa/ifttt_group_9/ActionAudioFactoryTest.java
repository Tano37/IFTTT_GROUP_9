package it.unisa.ifttt_group_9;

import it.unisa.ifttt_group_9.Action.ActionAudio;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ActionAudioFactoryTest {
    @Test
    void testCreateAction() {
        ActionAudioFactory factory = new ActionAudioFactory();
        String filepath = "/this/is/a/test/filepath";

        ActionAudio actionAudio = factory.createAction(filepath);

        assertNotNull(actionAudio, "ActionAudio instance should not be null");
        assertEquals(filepath, actionAudio.getFilePath(), "ActionText message isn't the one expected");

    }
}