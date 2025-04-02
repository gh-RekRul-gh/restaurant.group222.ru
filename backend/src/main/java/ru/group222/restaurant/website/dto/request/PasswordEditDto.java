package ru.group222.restaurant.website.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Schema(description = "Сущность для редактирования пароля пользователя")
public record PasswordEditDto(
        @Schema(description = "Id пользователя", example = "1")
        @NotNull(message = "User id must not be null")
        Long userId,

        @Schema(description = "Пароль пользователя", example = "any_string")
        @NotBlank(message = "The given password must not be blank")
        @Size(min = 6, max = 100, message = "Password length must be between 6 and 100 symbols")
        String password
) {
}
