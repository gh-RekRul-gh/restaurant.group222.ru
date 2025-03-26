package ru.group222.restaurant.website.dto.response;

import ru.group222.restaurant.website.exception.ApiException;

public record ApiError(
        String message
) {
    public ApiError(ApiException exception) {
        this(exception.getMessage());
    }
}
