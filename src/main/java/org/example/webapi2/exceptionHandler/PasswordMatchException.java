package org.example.webapi2.exceptionHandler;

public class PasswordMatchException extends RuntimeException {
    public PasswordMatchException() {}
    public PasswordMatchException(String message) {
        super(message);
    }
}
