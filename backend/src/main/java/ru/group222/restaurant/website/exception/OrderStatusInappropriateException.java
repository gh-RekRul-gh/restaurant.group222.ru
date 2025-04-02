package ru.group222.restaurant.website.exception;

public class OrderStatusInappropriateException extends BadRequestException {
    public OrderStatusInappropriateException(String message) {
        super(message);
    }
}
