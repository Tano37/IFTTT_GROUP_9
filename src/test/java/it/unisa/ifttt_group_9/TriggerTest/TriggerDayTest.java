package it.unisa.ifttt_group_9.TriggerTest;
import it.unisa.ifttt_group_9.Trigger.Trigger;
import it.unisa.ifttt_group_9.Trigger.TriggerDay;
import it.unisa.ifttt_group_9.exceptions.IllegalTimeException;
import org.junit.jupiter.api.Test;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class TriggerDayTest {

    @Test
    public void testTriggerDayWithValidDay() {
        assertDoesNotThrow(() -> {
            Trigger trigger = new TriggerDay(LocalDate.now().getDayOfWeek().getValue());
            assertTrue(trigger.evaluate());
        });
    }
    @Test
    public void testTriggerDayWithNotValidDay() {
        assertDoesNotThrow(() -> {
            Trigger trigger = new TriggerDay(LocalDate.now().getDayOfWeek().getValue()-1);
            assertFalse(trigger.evaluate());
        });
    }


    @Test
    public void testTriggerDayWithInvalidDay() {
        assertThrows(IllegalTimeException.class, () -> {
            new TriggerDay(8);
        });
    }

    @Test
    public void testTriggerDayWithNegate() {
        Trigger trigger = new TriggerDay(LocalDate.now().getDayOfWeek().getValue(), true);
        assertFalse(trigger.evaluate());
    }

    @Test
    public void testTriggerDayWithNextTriggerAndOr() {
        Trigger trigger = new TriggerDay(4, false, null, true);
        // Add your assertion based on your specific logic
        // assertTrue(trigger.evaluate());
    }




}
