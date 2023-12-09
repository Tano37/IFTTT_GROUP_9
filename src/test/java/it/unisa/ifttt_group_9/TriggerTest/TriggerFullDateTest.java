package it.unisa.ifttt_group_9.TriggerTest;

import it.unisa.ifttt_group_9.Trigger.Trigger;
import it.unisa.ifttt_group_9.Trigger.TriggerFullDate;
import it.unisa.ifttt_group_9.exceptions.IllegalTimeException;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class TriggerFullDateTest {

    @Test
    void testTriggerFullDateWithValidDate() {
        assertDoesNotThrow(() -> {
            Trigger trigger = new TriggerFullDate(LocalDateTime.now().getDayOfMonth(), LocalDate.now().getMonthValue(), LocalDate.now().getYear());

            assertTrue(trigger.evaluate());
        });
    }
    @Test
    void testTriggerFullDateWithNotValidDate() {
        assertDoesNotThrow(() -> {
            Trigger trigger = new TriggerFullDate(LocalDateTime.now().getDayOfMonth()-1, LocalDate.now().getMonthValue()-1, LocalDate.now().getYear()-1);

            assertFalse(trigger.evaluate());
        });
    }

    @Test
    void testTriggerFullDateWithInvalidDate() {
        assertThrows(IllegalTimeException.class, () -> {
            new TriggerFullDate(32, 12, 2023);
        });
    }

    @Test
    void testTriggerFullDateWithNegate() {
        Trigger trigger = new TriggerFullDate(LocalDateTime.now().getDayOfMonth(), LocalDate.now().getMonthValue(), LocalDate.now().getYear(),true);
        assertFalse(trigger.evaluate());
    }
    @Test
    void testTriggerFullDateWithNegate2() {
        Trigger trigger = new TriggerFullDate(3, 12, 2023, true);
        assertTrue(trigger.evaluate());
    }

    @Test
    void testTriggerFullDateWithNextTriggerAndOr() {
        Trigger trigger = new TriggerFullDate(3, 12, 2023, false, null, true);
        // Add your assertion based on your specific logic
        // assertTrue(trigger.evaluate());
    }


    @Test
    void testTriggerFullDateToString() {
        Trigger trigger = new TriggerFullDate(3, 12, 2023);
        assertEquals("{TriggerFullDate: 3\\12\\2023 }\n", trigger.toString());
    }
}
