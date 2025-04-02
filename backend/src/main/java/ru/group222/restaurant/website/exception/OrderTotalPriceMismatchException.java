package ru.group222.restaurant.website.exception;

public class OrderTotalPriceMismatchException extends BadRequestException {
    public OrderTotalPriceMismatchException(String message) {
        super(message);
    }
}
