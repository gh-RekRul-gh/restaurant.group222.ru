package ru.group222.restaurant.website.exception;

public class CartItemOwnershipException extends ForbiddenException {
    public CartItemOwnershipException(String message) {
        super(message);
    }
}
