package ru.group222.restaurant.website.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

@Schema(description = "Сущность заказа")
public record OrderResponseDto(
        @Schema(description = "Id заказа", example = "1")
        Long orderId,

        @Schema(description = "Статус заказа", example = "Готовится")
        String status,

        @Schema(description = "Итоговая сумма заказа с доставкой", example = "17100.00")
        BigDecimal totalPrice,

        @Schema(description = "Дата и время создания заказа", example = "2025-03-28 22:30:30")
        Instant createdAt,

        @Schema(description = "Номер телефона к заказу", example = "+79998887766")
        String phoneNumber,

        @Schema(description = "Адрес доставки", example = "г. Москва, пр-кт Вернадского, д. 78")
        String address,

        @Schema(description = "Комментарий курьеру к заказу", example = "Ждите у ворот вуза")
        String courierComment,

        @Schema(description = "Способ оплаты заказа", example = "Оплата картой")
        String paymentMethod,

        @Schema(description = "Позиции заказа")
        List<OrderItemResponseDto> items
) {
}
