package it.unisa.ifttt_group_9.ExceptionsTest;

import it.unisa.ifttt_group_9.exceptions.IllegalTimeException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class IllegalTimeExceptionTest {

    @Test
    void testDefaultConstructor() {
        IllegalTimeException exception = assertThrows(IllegalTimeException.class, () -> {
            throw new IllegalTimeException();
        });

        assertNull(exception.getMessage());
    }

    @Test
    void testMessageConstructor() {
        String errorMessage = "Illegal time!";
        IllegalTimeException exception = assertThrows(IllegalTimeException.class, () -> {
            throw new IllegalTimeException(errorMessage);
        });

        assertEquals(errorMessage, exception.getMessage());
    }
}
