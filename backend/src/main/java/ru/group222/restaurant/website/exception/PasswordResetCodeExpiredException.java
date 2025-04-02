package ru.group222.restaurant.website.exception;

public class PasswordResetCodeExpiredException extends BadRequestException {
    public PasswordResetCodeExpiredException(String message) {
        super(message);
    }
}
