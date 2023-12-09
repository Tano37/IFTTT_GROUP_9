package it.unisa.ifttt_group_9.ActionTest;

import it.unisa.ifttt_group_9.Action.Action;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ActionTest {

    @Test
    void testExecuteAction() {
        // Implementazione di Action per il test
        class SampleAction implements Action {
            private boolean executed = false;

            @Override
            public void executeAction() {
                executed = true;
            }

            public boolean isExecuted() {
                return executed;
            }
        }

        // Crea un'istanza dell'implementazione di Action
        Action sampleAction = new SampleAction();

        // Esegui l'azione
        sampleAction.executeAction();

        // Verifica che l'azione sia stata eseguita correttamente
        assertTrue(((SampleAction) sampleAction).isExecuted());
    }
}
