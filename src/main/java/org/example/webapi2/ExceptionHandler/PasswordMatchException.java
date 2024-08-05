package org.example.webapi2.ExceptionHandler;

public class PasswordMatchException extends RuntimeException {
    public PasswordMatchException() {}
    public PasswordMatchException(String message) {
        super(message);
    }
}
