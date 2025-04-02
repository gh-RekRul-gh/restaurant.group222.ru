package ru.group222.restaurant.website.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.math.BigDecimal;

@Schema(description = "Сущность для оформления заказа")
public record OrderCreationDto(
        @Schema(description = "Id пользователя", example = "1")
        @NotNull(message = "User id must not be null")
        Long userId,

        @Schema(description = "Итоговая стоимость заказа", example = "3800")
        @NotNull(message = "Total price must not be null")
        BigDecimal totalPrice,

        @Schema(description = "Номер телефона к заказу", example = "+79998887766")
        @NotBlank(message = "The given phone number must not be blank")
        @Pattern(regexp = "\\+7(\\d{10})", message = "Invalid phone number")
        String phoneNumber,

        @Schema(description = "Адрес доставки", example = "г. Москва, пр-кт Вернадского, д. 78")
        @NotBlank(message = "The given address must not be blank")
        String address,

        @Schema(description = "Комментарий курьеру к заказу", example = "Ждите у ворот вуза")
        @NotBlank(message = "The given courier comment must not be blank")
        String courierComment,

        @Schema(description = "Способ оплаты заказа", example = "Оплата картой")
        @NotBlank(message = "The given payment method must not be blank")
        String paymentMethod
) {
}
