package it.unisa.ifttt_group_9.ActionTest;

import it.unisa.ifttt_group_9.Action.Action;
import it.unisa.ifttt_group_9.Action.ActionText;
import it.unisa.ifttt_group_9.CounterManager;
import it.unisa.ifttt_group_9.exceptions.IllegalMessageException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ActionTextTest {

    @Test
    public void testActionTextConstructor() throws IllegalMessageException {
        Action action = new MockAction();
        ActionText actionText = new ActionText("Test Text", action, true);

        assertEquals("Test Text", actionText.getText());
        assertEquals(action, actionText.getAction());
        assertEquals(true, actionText.isVariableSubstitution());

        // Test constructor with null action
        actionText = new ActionText("Test Text", null, false);
        assertEquals("Test Text", actionText.getText());
        assertEquals(null, actionText.getAction());
        assertEquals(false, actionText.isVariableSubstitution());

        // Test constructor with empty text (should throw IllegalMessageException)
        assertThrows(IllegalMessageException.class, () -> new ActionText("", action, false));
    }

    @Test
    public void testExecuteAction() {
        Action action = new MockAction();
        ActionText actionText = new ActionText("Test Text", action, true);

        // Mock the counter substitution
        CounterManager.setCounterSubstitutionResult("Substituted Text");

        // Test executeAction
        actionText.executeAction();

        // Verify that the text is substituted
        assertEquals("Substituted Text", actionText.getText());
    }

    // A mock Action class for testing
    private static class MockAction implements Action {
        @Override
        public void executeAction() {
            // Mock action
        }

        @Override
        public String toString() {
            return "MockAction";
        }
    }
}
