package ru.group222.restaurant.website.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

@Schema(description = "Сущность с Id пользователя")
public record UserIdDto(
        @Schema(description = "Id пользователя", example = "1")
        @NotNull(message = "User id must not be null")
        Long userId
) {
}
