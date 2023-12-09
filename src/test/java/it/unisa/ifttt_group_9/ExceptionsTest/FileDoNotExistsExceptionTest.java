package it.unisa.ifttt_group_9.ExceptionsTest;

import it.unisa.ifttt_group_9.exceptions.FileDoNotExistsException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class FileDoNotExistsExceptionTest {

    @Test
    void testDefaultConstructor() {
        FileDoNotExistsException exception = assertThrows(FileDoNotExistsException.class, () -> {
            throw new FileDoNotExistsException();
        });

        assertNull(exception.getMessage());
    }

    @Test
    void testMessageConstructor() {
        String errorMessage = "File does not exist!";
        FileDoNotExistsException exception = assertThrows(FileDoNotExistsException.class, () -> {
            throw new FileDoNotExistsException(errorMessage);
        });

        assertEquals(errorMessage, exception.getMessage());
    }
}
