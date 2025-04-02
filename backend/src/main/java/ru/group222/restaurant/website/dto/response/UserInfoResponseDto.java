package ru.group222.restaurant.website.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Сущность информации о пользователе")
public record UserInfoResponseDto(
        @Schema(description = "Имя пользователя", example = "any_string")
        String name,

        @Schema(description = "Почта пользователя", example = "somemail@mail.ru")
        String email,

        @Schema(description = "Телефон пользователя", example = "8005553535")
        String phoneNumber
) {
}
