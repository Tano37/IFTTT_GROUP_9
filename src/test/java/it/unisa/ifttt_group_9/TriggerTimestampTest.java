package it.unisa.ifttt_group_9;

import it.unisa.ifttt_group_9.TriggerTimestamp;
import it.unisa.ifttt_group_9.exceptions.IllegalTimeException;
import org.junit.jupiter.api.Test;

import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

class TriggerTimestampTest {

    @Test
    public void testEvaluate(){
        LocalTime now= LocalTime.now();
        int h= now.getHour();
        int m= now.getMinute();

        TriggerTimestamp trigger = new TriggerTimestamp(h,m);
        assertTrue(trigger.evaluate());
    }

    @Test
    public void testTriggerTimestamp(){
        assertThrows(IllegalTimeException.class,
                () -> {
                    TriggerTimestamp trigger = new TriggerTimestamp(24, 61);
                });
    }

}