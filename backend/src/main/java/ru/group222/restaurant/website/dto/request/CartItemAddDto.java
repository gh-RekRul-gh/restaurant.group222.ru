package ru.group222.restaurant.website.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

@Schema(description = "Сущность для добавления блюда")
public record CartItemAddDto(
        @Schema(description = "Id блюда на добавление", example = "1")
        @NotNull(message = "Menu item id must not be null")
        Long menuItemId,

        @Schema(description = "Количество данной блюда на добавление", example = "5")
        @NotNull(message = "Menu item quantity must not be null")
        Integer quantity
) {
}
