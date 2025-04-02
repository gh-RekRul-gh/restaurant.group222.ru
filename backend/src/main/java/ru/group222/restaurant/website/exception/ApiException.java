package ru.group222.restaurant.website.exception;

import lombok.Getter;

@Getter
public class ApiException extends RuntimeException {
    public ApiException(String message) {
        super(message);
    }
}

