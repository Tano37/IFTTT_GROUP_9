package it.unisa.ifttt_group_9.TriggerTest;

import it.unisa.ifttt_group_9.Trigger.TriggerDay;
import it.unisa.ifttt_group_9.exceptions.IllegalTimeException;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class TriggerDayTest {

    @Test
    void evaluate_shouldReturnTrue_whenCurrentTimeMatchesTriggerTimeAndDay() throws IllegalTimeException {
        // Arrange
        int hour = LocalDateTime.now().getHour();
        int minute = LocalDateTime.now().getMinute();
        int day = LocalDateTime.now().getDayOfWeek().getValue(); // 4 rappresenta il mercoledì, dove 1 è il lunedì e 7 è la domenica
        TriggerDay triggerDay = new TriggerDay(hour, minute, day);

        // Act
        boolean result = triggerDay.evaluate();

        // Assert
        assertTrue(result, "Expected evaluation to be true");
    }

    @Test
    void evaluate_shouldReturnFalse_whenCurrentTimeDoesNotMatchTriggerTimeOrDay() throws IllegalTimeException {
        // Arrange
        int hour = 8;
        int minute = 45;
        int day = 2; // Assuming Tuesday
        TriggerDay triggerDay = new TriggerDay(hour, minute, day);

        // Act
        boolean result = triggerDay.evaluate();

        // Assert
        assertFalse(result, "Expected evaluation to be false");
    }

    @Test
    void evaluate_shouldThrowIllegalTimeException_whenInvalidTimeIsProvided() {
        // Arrange
        int invalidHour = 25;
        int minute = 30;
        int day = 3;

        // Act & Assert
        assertThrows(IllegalTimeException.class, () -> new TriggerDay(invalidHour, minute, day));
    }
}
