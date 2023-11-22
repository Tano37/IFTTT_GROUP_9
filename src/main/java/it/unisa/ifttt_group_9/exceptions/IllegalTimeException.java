package it.unisa.ifttt_group_9.exceptions;

public class IllegalTimeException extends RuntimeException {

    public IllegalTimeException() {
        super();
    }

    public IllegalTimeException(String message) {
        super(message);
    }
}