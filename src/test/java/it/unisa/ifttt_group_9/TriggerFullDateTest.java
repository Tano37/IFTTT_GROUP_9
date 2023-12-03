package it.unisa.ifttt_group_9;

import it.unisa.ifttt_group_9.Trigger.TriggerFullDate;
import it.unisa.ifttt_group_9.exceptions.IllegalTimeException;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class TriggerFullDateTest {

    @Test
    void evaluateTriggerOnMatchingDate() throws IllegalTimeException {
        // Imposta una data e un'ora che corrispondono a "now" per il test
        int testHour = LocalDateTime.now().getHour();
        int testMinute = LocalDateTime.now().getMinute();
        int testDay = LocalDateTime.now().getDayOfMonth(); // Lunedì
        int testMonth = LocalDateTime.now().getMonth().getValue();
        int testYear = LocalDateTime.now().getYear();

        // Crea un'istanza di TriggerFullDate con la data e l'ora di test
        TriggerFullDate trigger = new TriggerFullDate(testHour, testMinute, testDay, testMonth, testYear);

        // Valuta il trigger sulla data impostata
        boolean result = trigger.evaluate();

        // Verifica che il risultato sia vero, poiché la data corrisponde
        assertTrue(result);
    }

    @Test
    void evaluateTriggerOnNonMatchingDate() throws IllegalTimeException {
        // Imposta una data e un'ora che NON corrispondono a "now" per il test
        int testHour = 10;
        int testMinute = 45;
        int testDayOfWeek = 4; // Giovedì
        int testDayOfMonth = 20;
        int testYear = 2023;

        // Crea un'istanza di TriggerFullDate con la data e l'ora di test
        TriggerFullDate trigger = new TriggerFullDate(testHour, testMinute, testDayOfWeek, testDayOfMonth, testYear);

        // Valuta il trigger sulla data impostata
        boolean result = trigger.evaluate();

        // Verifica che il risultato sia falso, poiché la data NON corrisponde
        assertFalse(result);
    }

    @Test
    void constructorWithInvalidTimeThrowsException() {
        // Prova a creare un'istanza con un'ora, un minuto o un giorno del mese non validi
        assertThrows(IllegalTimeException.class, () -> new TriggerFullDate(-1, 30, 2, 15, 2023));
        assertThrows(IllegalTimeException.class, () -> new TriggerFullDate(15, 60, 2, 15, 2023));
        assertThrows(IllegalTimeException.class, () -> new TriggerFullDate(15, 30, 2, 40, 2023));
    }
}
