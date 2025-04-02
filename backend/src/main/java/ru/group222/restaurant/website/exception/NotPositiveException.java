package ru.group222.restaurant.website.exception;

public class NotPositiveException extends BadRequestException {
    public NotPositiveException(String message) {
        super(message);
    }
}
