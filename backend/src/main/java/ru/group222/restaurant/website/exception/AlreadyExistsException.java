package ru.group222.restaurant.website.exception;

public class AlreadyExistsException extends ConflictException {
    public AlreadyExistsException(String message) {
        super(message);
    }
}
