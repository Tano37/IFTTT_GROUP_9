package it.unisa.ifttt_group_9.TriggerTest;

import it.unisa.ifttt_group_9.Counters.Counter;
import it.unisa.ifttt_group_9.Trigger.TriggerCounter;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TriggerCounterTest {

    @Test
    public void testGreaterComparison() {
        Counter counter1 = new Counter("name1", 5);
        Counter counter2 = new Counter("name2", 3);
        TriggerCounter triggerCounter = new TriggerCounter(counter1, counter2, "greater", false, null, false);

        assertTrue(triggerCounter.evaluate());
    }

    @Test
    public void testLessComparison() {
        Counter counter1 = new Counter("name1", 3);
        Counter counter2 = new Counter("name2", 5);
        TriggerCounter triggerCounter = new TriggerCounter(counter1, counter2, "less", false, null, false);

        assertTrue(triggerCounter.evaluate());
    }

    @Test
    public void testEqualComparison() {
        Counter counter1 = new Counter("name1", 5);
        Counter counter2 = new Counter("name2", 5);
        TriggerCounter triggerCounter = new TriggerCounter(counter1, counter2, "equal", false, null, false);

        assertTrue(triggerCounter.evaluate());
    }

    @Test
    public void testNegatedComparison() {
        Counter counter1 = new Counter("name1", 5);
        Counter counter2 = new Counter("name2", 3);
        TriggerCounter triggerCounter = new TriggerCounter(counter1, counter2, "greater", true, null, false);

        assertFalse(triggerCounter.evaluate());
    }

    // Add more test cases as needed based on your specific scenarios.
}
