package it.unisa.ifttt_group_9;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class ControllerCounterTest {

    private ControllerCounter controller;
    private ObservableList<Counter> counterList;

    @BeforeEach
    public void setUp() {
        // Initialize the counterList and ControllerCounter before each test
        counterList = FXCollections.observableArrayList();
        controller = new ControllerCounter(counterList);
    }

    @Test
    public void testCreation() {
        // Simulate user input for creating a counter
        // You might need to adjust this based on your specific requirements
        creation("Test",10);

        // Assert that the counterList is not empty after creation
        assertFalse(counterList.isEmpty());
    }

    @Test
    public void testDelete() {
        // Add a counter to the list for testing deletion
        Counter testCounter = new Counter("TestCounter", 10);
        counterList.add(testCounter);

        // Simulate user input for deleting a counter
        controller.delete(FXCollections.observableArrayList(testCounter));

        // Assert that the counterList is empty after deletion
        assertTrue(counterList.isEmpty());
    }

    @Test
    public void testUpdate() {
        // Add counters to the list for testing update
        Counter counter1 = new Counter("Counter1", 5);

        counterList.addAll(counter1);

        // Simulate user input for updating counters
        update(counter1,10);

        // Assert that the counters have been updated
        assertEquals(10, counter1.getValue());

    }

    @Test
    public void testSaveAndLoadCounterList() {
        // Add a counter to the list for testing save and load
        Counter testCounter = new Counter("TestCounter", 15);
        counterList.add(testCounter);

        try {
            // Save the counter list to a file
            controller.saveCounterList();

            // Clear the counter list
            counterList.clear();

            // Load the counter list from the file
            controller.loadCounterList();

            // Assert that the loaded counter list contains the expected counter
            assertTrue(counterList.contains(testCounter));
        } catch (IOException e) {
            fail("Exception thrown during save or load: " + e.getMessage());
        }
    }

    //Ho usato un metodo creation simuato in quanto non testando l'interfaccia grafica non posso
    //prendere valori dall'utente quindi devo inserirli a mano per fare il test. Lo stesso vale per
    //il metodo di modifica dopo
    public void creation(String name, int value){


        // Ora puoi utilizzare enteredName e enteredValue come desideri
        Counter count = new Counter(name, value);
        if (counterList != null) {

            counterList.add(count);
           /* try {
                saveCounterList();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }*/
        }
    }

public void update(Counter counterToUpdate, int updatedNumber){

}
}




