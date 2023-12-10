package it.unisa.ifttt_group_9.CounterTest;

import it.unisa.ifttt_group_9.Counters.Counter;
import it.unisa.ifttt_group_9.Counters.CounterManager;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CounterManagerTest {

    private CounterManager counterManager= CounterManager.getInstance();

    @Test
    public void testAddCounter() {
        Counter counter = new Counter("Counter1", 1);
        counterManager.addCounter(counter);
        assertTrue(counterManager.getCounterList().contains(counter));
    }

    @Test
    public void getCounterByName() {
        Counter counter2 = new Counter("Counter2", 2);
        counterManager.addCounter(counter2);
        Counter exampleCounter = counterManager.getCounterByName("Counter2");
        assertNotNull(exampleCounter);
        assertEquals(counter2, exampleCounter);
    }

    @Test
    void counterSubstitution() {
        Counter counter3 = new Counter("Counter3", 15);
        counterManager.addCounter(counter3);

        String substitutedText = CounterManager.counterSubstitution("Il valore di Counter 3 è $Counter3");
        assertEquals(substitutedText, "Il valore di Counter 3 è 15");
        String wrongText = "Il valore di Counter 37 è $UnexistingCounter";
        String elaboratedWrongText = CounterManager.counterSubstitution("Il valore di Counter 37 è $UnexistingCounter");
        assertEquals(elaboratedWrongText, ("Almeno un counter desiderato non è disponibile non è disponibile per l'output: " + wrongText));
    }
}