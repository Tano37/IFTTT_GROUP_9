package it.unisa.ifttt_group_9;

import it.unisa.ifttt_group_9.exceptions.IllegalMessageException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class IllegalMessageExceptionTest {

    @Test
    void testDefaultConstructor() {
        IllegalMessageException exception = assertThrows(IllegalMessageException.class, () -> {
            throw new IllegalMessageException();
        });

        assertNull(exception.getMessage());
    }

    @Test
    void testMessageConstructor() {
        String errorMessage = "Illegal message!";
        IllegalMessageException exception = assertThrows(IllegalMessageException.class, () -> {
            throw new IllegalMessageException(errorMessage);
        });

        assertEquals(errorMessage, exception.getMessage());
    }
}
