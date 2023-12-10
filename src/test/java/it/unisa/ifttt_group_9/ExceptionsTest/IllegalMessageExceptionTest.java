package it.unisa.ifttt_group_9.ExceptionsTest;

import it.unisa.ifttt_group_9.exceptions.IllegalMessageException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class IllegalMessageExceptionTest {

    @Test
    public void testDefaultConstructor() {
        IllegalMessageException exception = assertThrows(IllegalMessageException.class, () -> {
            throw new IllegalMessageException();
        });

        assertNull(exception.getMessage());
    }

    @Test
    public void testMessageConstructor() {
        String errorMessage = "Illegal message!";
        IllegalMessageException exception = assertThrows(IllegalMessageException.class, () -> {
            throw new IllegalMessageException(errorMessage);
        });

        assertEquals(errorMessage, exception.getMessage());
    }
}
