package ru.group222.restaurant.website.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Schema(description = "Сущность для регистрации пользователя")
public record UserRegisterDto(
        @Schema(description = "Имя пользователя", example = "any_string")
        @NotBlank(message = "The given name must not be blank")
        @Size(max = 100, message = "User's name maximum length is 100 symbols")
        String name,

        @Schema(description = "Почта пользователя", example = "somemail@mail.ru")
        @Pattern(regexp = "^[a-zA-Z0-9_%+-]+[a-zA-Z0-9._%+-]+@[a-zA-Z0-9-]+[a-zA-Z0-9.-]+[a-zA-Z0-9-]+\\.[a-zA-Z]{2,}$",
                message = "Invalid email format")
        @NotBlank(message = "The given email must not be empty")
        @Size(max = 255, message = "Email maximum length is 255 symbols")
        String email,

        @Schema(description = "Пароль пользователя", example = "any_string")
        @NotBlank(message = "The given password must not be blank")
        @Size(min = 6, max = 100, message = "Password length must be between 6 and 100 symbols")
        String password
) {
}
