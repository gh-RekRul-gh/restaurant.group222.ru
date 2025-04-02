package ru.group222.restaurant.website.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;

@Schema(description = "Сущность блюда")
public record MenuItemResponseDto(
        @Schema(description = "Id блюда", example = "1")
        Long menuItemId,

        @Schema(description = "Url изображения блюда", example = "*some url*")
        String imageUrl,

        @Schema(description = "Название блюда", example = "Вкусняшка")
        String name,

        @Schema(description = "Описание блюда", example = "Это что-то вкусное")
        String description,

        @Schema(description = "Цена блюда", example = "1500")
        BigDecimal price,

        @Schema(description = "Id категории блюда", example = "1")
        Long categoryId
) {
}
