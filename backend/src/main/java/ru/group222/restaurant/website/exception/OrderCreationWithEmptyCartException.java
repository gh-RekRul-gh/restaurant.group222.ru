package ru.group222.restaurant.website.exception;

public class OrderCreationWithEmptyCartException extends BadRequestException {
    public OrderCreationWithEmptyCartException(String message) {
        super(message);
    }
}
