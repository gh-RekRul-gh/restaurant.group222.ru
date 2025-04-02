package ru.group222.restaurant.website.exception;

public class PasswordResetCodeNotFoundException extends BadRequestException {
    public PasswordResetCodeNotFoundException(String message) {
        super(message);
    }
}
