package com.developersjugad.votingsystem.exception;

public class ElectionNotFoundException extends RuntimeException {

    public ElectionNotFoundException() {
        super();
    }

    public ElectionNotFoundException(String message) {
        super(message);
    }

    public ElectionNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
