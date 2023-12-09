package it.unisa.ifttt_group_9.CounterTest;

import it.unisa.ifttt_group_9.Counter;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CounterTest {

    @Test
    public void testGetValue() {
        Counter counter = new Counter("TestCounter", 5);
        assertEquals(5, counter.getValue());
    }

    @Test
    public void testGetName() {
        Counter counter = new Counter("TestCounter", 5);
        assertEquals("TestCounter", counter.getName());
    }

    @Test
    public void testSetValue() {
        Counter counter = new Counter("TestCounter", 5);
        counter.setValue(10);
        assertEquals(10, counter.getValue());
    }

    @Test
    public void testAggiungi() {
        Counter counter = new Counter("TestCounter", 5);
        counter.aggiungi(3);
        assertEquals(8, counter.getValue());
    }

    @Test
    public void testToString() {
        Counter counter = new Counter("TestCounter", 5);
        assertEquals("name='TestCounter', value=5", counter.toString());
    }
}
