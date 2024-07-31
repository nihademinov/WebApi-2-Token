package org.example.webapi2.api.bussines.management;

public class NotFoundExceptionManager extends RuntimeException {
    public NotFoundExceptionManager(String message) {
        super(message);
    }
}
