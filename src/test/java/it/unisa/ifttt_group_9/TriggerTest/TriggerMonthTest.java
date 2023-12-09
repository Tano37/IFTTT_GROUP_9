package it.unisa.ifttt_group_9.TriggerTest;

import it.unisa.ifttt_group_9.Trigger.Trigger;
import it.unisa.ifttt_group_9.Trigger.TriggerMonth;
import it.unisa.ifttt_group_9.exceptions.IllegalTimeException;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class TriggerMonthTest {

    //Test with the same date
    @Test
    void testTriggerMonthWithCurrentDay() {
        assertDoesNotThrow(() -> {
            Trigger trigger = new TriggerMonth(LocalDate.now().getDayOfMonth());

            assertTrue(trigger.evaluate());
        });
    }
    @Test
    void testTriggerMonthWithNotValidDay() {
        assertDoesNotThrow(() -> {
            Trigger trigger = new TriggerMonth(LocalDate.now().getDayOfMonth()-1);

            assertFalse(trigger.evaluate());
        });
    }

    @Test
    void testTriggerMonthWithInvalidDay() {
        assertThrows(IllegalTimeException.class, () -> {
            new TriggerMonth(40);
        });
    }

    @Test
    void testTriggerMonthWithNegateRightDay() {
        Trigger trigger = new TriggerMonth(LocalDate.now().getDayOfMonth(), true);
        assertFalse(trigger.evaluate());
    }
    @Test
    void testTriggerMonthWithNegateWrongDay() {
        Trigger trigger = new TriggerMonth(LocalDate.now().getDayOfMonth()-1, true);
        assertTrue(trigger.evaluate());
    }



    @Test
    void testTriggerMonthWithNextTriggerAndOr() {
        Trigger trigger = new TriggerMonth(20, false, null, true);

    }

    @Test
    void testTriggerMonthToString() {
        Trigger trigger = new TriggerMonth(5);
        assertEquals("{TriggerMonth: 5 }\n", trigger.toString());
    }
}
