package it.unisa.ifttt_group_9;

import it.unisa.ifttt_group_9.Controller.PanelPopUPManager;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PanelPopUPManagerTest {

    @Test
    void testShowMessage() {
        // Dato che il metodo showMessage coinvolge interazioni con l'interfaccia grafica (GUI), potresti voler testarlo manualmente o ispezionare visivamente l'output.

        // Crea un'istanza di PanelPopUPManager
        PanelPopUPManager panelPopUPManager = new PanelPopUPManager("Titolo di Test", "Messaggio di Test");

        // Invoca il metodo showMessage
        int result = panelPopUPManager.showMessage();

        // In questo esempio, puoi stampare il risultato per la verifica manuale
        System.out.println("Risultato del dialogo: " + result);

        // Potresti voler ispezionare visivamente il dialogo visualizzato e verificare se soddisfa le tue aspettative.
        // Per il testing automatizzato delle interazioni GUI, potresti dover utilizzare strumenti o framework aggiuntivi.
    }
}
