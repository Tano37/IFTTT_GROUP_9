package it.unisa.ifttt_group_9;

import it.unisa.ifttt_group_9.Controller.PanelPopUPManager;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PanelPopUPManagerTest {

    @Test
    public void testShowMessage() {
        // Dato che il metodo showMessage coinvolge interazioni con l'interfaccia grafica (GUI), potresti voler testarlo manualmente o ispezionare visivamente l'output.

        // Creating a PanelPopUPManager istance
        PanelPopUPManager panelPopUPManager = new PanelPopUPManager("Titolo di Test", "Messaggio di Test");

        // Calling showMessage method
        int result = panelPopUPManager.showMessage();

        // Asserting that the pop-up has been showed and closed in one of the two allowed ways
        assertTrue((0 == result) || (-1 == result));

    }
}
