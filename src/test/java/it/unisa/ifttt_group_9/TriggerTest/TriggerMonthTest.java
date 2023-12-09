package it.unisa.ifttt_group_9.TriggerTest;


import it.unisa.ifttt_group_9.Trigger.TriggerMonth;
import it.unisa.ifttt_group_9.exceptions.IllegalTimeException;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class TriggerMonthTest {

    @Test
    public void testEvaluateTrue() throws IllegalTimeException {
        // Testa il metodo evaluate quando la condizione è vera
        int testHour = LocalDateTime.now().getHour();
        int testMinute = LocalDateTime.now().getMinute();
        int testDayWeek = 0;
        int testDayMonth = LocalDateTime.now().getDayOfMonth(); // Usa il giorno corrente

        TriggerMonth trigger = new TriggerMonth(testHour, testMinute, testDayWeek, testDayMonth);

        assertTrue(trigger.evaluate());
    }

    @Test
    public void testEvaluateFalse() throws IllegalTimeException {
        // Testa il metodo evaluate quando la condizione è falsa
        int testHour = 12;
        int testMinute = 30;
        int testDayWeek = 0;
        int testDayMonth = LocalDateTime.now().getDayOfMonth() + 1; // Usa il giorno successivo

        TriggerMonth trigger = new TriggerMonth(testHour, testMinute, testDayWeek, testDayMonth);

        assertFalse(trigger.evaluate());
    }

    @Test
    public void testIllegalTimeException() {
        // Testa se viene lanciata IllegalTimeException per valori non validi
        assertThrows(IllegalTimeException.class, () -> new TriggerMonth(-1, 30, 0, 1));
        assertThrows(IllegalTimeException.class, () -> new TriggerMonth(12, 60, 0, 1));
        assertThrows(IllegalTimeException.class, () -> new TriggerMonth(12, 30, 0, 32));
    }
}

