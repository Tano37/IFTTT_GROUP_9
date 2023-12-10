package it.unisa.ifttt_group_9.CounterTest;
import it.unisa.ifttt_group_9.Counters.ControllerCounter;
import it.unisa.ifttt_group_9.Counters.Counter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import static org.junit.jupiter.api.Assertions.*;

class ControllerCounterTest {

    private ControllerCounter controllerCounter;
    private ObservableList<Counter> counterList;

    @BeforeEach
    void setUp() {
        // Inizializza la ControllerCounter con una lista vuota
        counterList = FXCollections.observableArrayList();
        controllerCounter = new ControllerCounter(counterList);
    }

    @Test
    void testCreation() throws IOException {
        // Simula l'input dell'utente
        creation("TestCounter", "42");

        // Verifica che il contatore sia stato creato correttamente
        assertEquals(1, counterList.size());
        assertEquals("TestCounter", counterList.get(0).getName());
        assertEquals(42, counterList.get(0).getValue());
    }

    @Test
    void testDelete() throws IOException {
        // Aggiungi alcuni contatori alla lista
        Counter counter1 = new Counter("Counter1", 10);
        Counter counter2 = new Counter("Counter2", 20);
        counterList.addAll(counter1, counter2);

        // Simula la selezione di alcuni elementi
        ObservableList<Counter> selectedItems = FXCollections.observableArrayList(counter1, counter2);

        // Esegui il metodo da testare
        controllerCounter.delete(selectedItems);

        // Verifica che i contatori siano stati rimossi correttamente
        assertTrue(counterList.isEmpty());
    }

    @Test
    void testSaveCounterList() throws IOException {
        // Aggiungi alcuni contatori alla lista
        Counter counter1 = new Counter("Counter1", 10);
        Counter counter2 = new Counter("Counter2", 20);
        counterList.addAll(counter1, counter2);

        // Esegui il metodo da testare
        controllerCounter.saveCounterList();

        // Verifica che la lista dei contatori sia stata salvata correttamente
        // Puoi anche aggiungere ulteriori verifiche in base alla tua implementazione
        assertTrue(counterList.size() == 2); // La dimensione dovrebbe rimanere la stessa dopo il salvataggio
        counterList.clear(); // "Simula" la creazione di una nuova lista al caricamento
        controllerCounter.loadCounterList();
        assertEquals(2, counterList.size()); // Dopo il caricamento, la dimensione dovrebbe essere nuovamente 2
    }

    @Test
    void testLoadCounterList() throws IOException {
        // Aggiungi alcuni contatori alla lista
        Counter counter1 = new Counter("Counter1", 10);
        Counter counter2 = new Counter("Counter2", 20);
        counterList.addAll(counter1, counter2);

        // Esegui il metodo da testare
        controllerCounter.saveCounterList();

        // Verifica che la lista dei contatori sia stata caricata correttamente
        // Puoi anche aggiungere ulteriori verifiche in base alla tua implementazione
        assertFalse(counterList.isEmpty()); // La lista non dovrebbe essere vuota dopo il caricamento
        assertEquals(2, counterList.size()); // Dopo il caricamento, la dimensione dovrebbe essere 2
        // Puoi anche verificare i dettagli specifici dei contatori caricati, ad esempio, controllare che contengano i dati corretti
        assertEquals("Counter1", counterList.get(0).getName());
        assertEquals(10, counterList.get(0).getValue());
        assertEquals("Counter2", counterList.get(1).getName());
        assertEquals(20, counterList.get(1).getValue());
    }
    public void creation(String name, String value){

            String enteredName = name;
            Integer enteredValue = Integer.parseInt(value);

            Counter count = new Counter(enteredName, enteredValue);
            if (counterList != null) {

                counterList.add(count);
            }
        }
    }

