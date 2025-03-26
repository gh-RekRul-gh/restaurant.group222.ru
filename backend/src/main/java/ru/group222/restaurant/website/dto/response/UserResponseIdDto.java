package ru.group222.restaurant.website.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;

public record UserResponseIdDto(
        @Schema(description = "Id пользователя", example = "1")
        Long id
){
}
