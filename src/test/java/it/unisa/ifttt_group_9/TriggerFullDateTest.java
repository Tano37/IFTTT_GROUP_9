package it.unisa.ifttt_group_9;

import it.unisa.ifttt_group_9.TriggerFullDate;
import it.unisa.ifttt_group_9.exceptions.IllegalTimeException;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class TriggerFullDateTest {

    @Test
    void evaluateShouldReturnTrueWhenDateTimeMatches() throws IllegalTimeException {

        int testHour = LocalDateTime.now().getHour();
        int testMinute = LocalDateTime.now().getMinute();
        int testDayWeek = LocalDateTime.now().getDayOfWeek().getValue();
        int testDayMonth = LocalDateTime.now().getDayOfMonth();
        int testYear = LocalDateTime.now().getYear();

        TriggerFullDate trigger = new TriggerFullDate(testHour, testMinute, testDayWeek, testDayMonth, testYear);

        assertTrue(trigger.evaluate());
    }

    /*@Test
    void evaluateShouldReturnFalseWhenDateTimeDoesNotMatch() throws IllegalTimeException {
        int testHour = 12;
        int testMinute = 30;
        int testDayWeek = 2;
        int testDayMonth = 15;
        int testYear = 2023;

        TriggerFullDate trigger = new TriggerFullDate(testHour, testMinute, testDayWeek, testDayMonth, testYear);

        // Change the current date to a different one
        //setCurrentDateTime(trigger, 2023, 3, 1, 10, 0);

        assertFalse(trigger.evaluate());
    }

    @Test
    void evaluateShouldReturnFalseWhenDateTimeMatchesButHourIsDifferent() throws IllegalTimeException {
        int testHour = 12;
        int testMinute = 30;
        int testDayWeek = 2;
        int testDayMonth = 15;
        int testYear = 2023;

        TriggerFullDate trigger = new TriggerFullDate(testHour, testMinute, testDayWeek, testDayMonth, testYear);

        // Change the current hour to make it different
        //setCurrentDateTime(trigger, 2023, 2, 15, 13, 0);

        assertFalse(trigger.evaluate());
    }

    // Add more tests as needed...

    // Helper method to set the current date and time for testing
    /*private void setCurrentDateTime(TriggerFullDate trigger, int year, int month, int dayOfMonth, int hour, int minute) {
        trigger.setCurrentDateTime(LocalDateTime.of(year, month, dayOfMonth, hour, minute));
    }*/
}
