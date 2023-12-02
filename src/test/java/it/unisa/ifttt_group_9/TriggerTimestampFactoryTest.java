package it.unisa.ifttt_group_9;

import it.unisa.ifttt_group_9.Trigger.Trigger;
import it.unisa.ifttt_group_9.Trigger.TriggerFactory;
import org.junit.jupiter.api.Test;

import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

class TriggerTimestampFactoryTest {
    @Test
    void testCreateTrigger() {
        // Arrange
        TriggerFactory factory = new TriggerFactory();


        LocalTime now= LocalTime.now();
        int h= now.getHour();
        int m= now.getMinute();

        Trigger trigger = factory.createTrigger(h, m);

        // Assert
        assertNotNull(trigger, "TriggerTimestamp instance shouldn't be null");
       /* assertEquals(LocalTime.now().getHour(), trigger.getHour(), "TriggerTimestamp hour doesn't match the expected");
        assertEquals(LocalTime.now().getMinute(), trigger.getMinute(), "TriggerTimestamp minute doesn't match the expected");*/
    }

}