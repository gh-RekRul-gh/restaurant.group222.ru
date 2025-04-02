package ru.group222.restaurant.website.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

import java.util.List;

@Schema(description = "Сущность для добавления нескольких блюд")
public record CartItemsAddDto(
        @Schema(description = "Id пользователя", example = "1")
        @NotNull(message = "User id must not be null")
        Long userId,

        @Schema(description = "Список блюд")
        @Valid
        List<CartItemAddDto> items
) {
}
