package it.unisa.ifttt_group_9.exceptions;

public class IllegalMessageException extends RuntimeException {

    public IllegalMessageException() {
        super();
    }

    public IllegalMessageException(String message) {
        super(message);
    }
}
