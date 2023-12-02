package it.unisa.ifttt_group_9;

import it.unisa.ifttt_group_9.Action.ActionText;
import it.unisa.ifttt_group_9.Action.ActionTextFactory;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ActionTextFactoryTest {

    @Test
    void testCreateAction() {
        ActionTextFactory factory = new ActionTextFactory();
        String expectedText = "Test message";

        ActionText actionText = factory.createAction(expectedText);

        assertNotNull(actionText, "ActionText instance should not be null");
        assertEquals(expectedText, actionText.getText(), "ActionText message isn't the one expected");
    }
}