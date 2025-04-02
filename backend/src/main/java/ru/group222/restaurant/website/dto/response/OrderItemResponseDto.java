package ru.group222.restaurant.website.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;

@Schema(description = "Сущность для позиции в заказе")
public record OrderItemResponseDto(
        @Schema(description = "Цена позиции в заказе", example = "1500")
        BigDecimal price,

        @Schema(description = "Количество позиции в корзине", example = "5")
        Integer quantity,

        @Schema(description = "Id блюда из меню", example = "1")
        Long menuItemId,

        @Schema(description = "Название блюда", example = "Вкусняшка")
        String name,

        @Schema(description = "Url изображения блюда", example = "*some url*")
        String imageUrl
) {
}
