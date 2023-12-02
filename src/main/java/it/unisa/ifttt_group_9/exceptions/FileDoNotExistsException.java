package it.unisa.ifttt_group_9.exceptions;

public class FileDoNotExistsException extends RuntimeException{

    public FileDoNotExistsException() {
        super();
    }

    public FileDoNotExistsException(String message) {
        super(message);
    }
}
