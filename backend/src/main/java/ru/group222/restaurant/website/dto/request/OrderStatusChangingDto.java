package ru.group222.restaurant.website.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Schema(description = "Сущность для изменения статуса заказа")
public record OrderStatusChangingDto(
        @Schema(description = "Id заказа", example = "1")
        @NotNull(message = "Order id must not be null")
        Long orderId,

        @Schema(description = "Новый статус для заказа", example = "Готовится")
        @NotBlank(message = "The given status name must not be blank")
        String statusName
) {
}
