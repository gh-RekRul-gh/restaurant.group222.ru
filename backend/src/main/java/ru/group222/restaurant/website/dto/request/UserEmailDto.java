package ru.group222.restaurant.website.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Schema(description = "Сущность с почтой пользователя")
public record UserEmailDto(
        @Schema(description = "Почта пользователя", example = "somemail@mail.ru")
        @Pattern(regexp = "^[a-zA-Z0-9_%+-]+[a-zA-Z0-9._%+-]+@[a-zA-Z0-9-]+[a-zA-Z0-9.-]+[a-zA-Z0-9-]+\\.[a-zA-Z]{2,}$",
                message = "Invalid email format")
        @NotBlank(message = "The given email must not be empty")
        @Size(max = 255, message = "Email maximum length is 255 symbols")
        String email
) {
}