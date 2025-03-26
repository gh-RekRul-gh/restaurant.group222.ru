package ru.group222.restaurant.website.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Сущность всей информации о пользователе")
public record UserDto(
        @Schema(description = "Имя пользователя", example = "any_string")
        String name,

        @Schema(description = "Почта пользователя", example = "somemail@mail.ru")
        String email,

        @Schema(description = "Пароль пользователя", example = "any_string")
        String password,

        @Schema(description = "Телефон пользователя", example = "8005553535")
        String phoneNumber
) {
}
