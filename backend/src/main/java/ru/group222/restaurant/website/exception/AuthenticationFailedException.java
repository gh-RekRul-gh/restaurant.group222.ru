package ru.group222.restaurant.website.exception;

public class AuthenticationFailedException extends BadRequestException {
    public AuthenticationFailedException(String message) {
        super(message);
    }
}