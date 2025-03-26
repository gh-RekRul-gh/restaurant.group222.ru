package ru.group222.restaurant.website.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Schema(description = "Сущность для сброса пароля пользователя")
public record PasswordResetDto(
        @Schema(description = "Код сброса пароля", example = "123456")
        @NotBlank(message = "The given code must not be blank")
        @Pattern(regexp = "\\d{8}", message = "Code must be exactly 8 digits")
        String code,

        @Schema(description = "Новый пароль пользователя", example = "any_string")
        @NotBlank(message = "The given password must not be blank")
        @Size(min = 6, max = 100, message = "Password length must be between 6 and 100 symbols")
        String password
) {
}
