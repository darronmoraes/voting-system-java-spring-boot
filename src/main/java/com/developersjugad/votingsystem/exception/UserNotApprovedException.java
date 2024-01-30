package com.developersjugad.votingsystem.exception;

public class UserNotApprovedException extends RuntimeException {

    public UserNotApprovedException() {
        super();
    }

    public UserNotApprovedException(String message) {
        super(message);
    }

    public UserNotApprovedException(String message, Throwable cause) {
        super(message, cause);
    }
}
