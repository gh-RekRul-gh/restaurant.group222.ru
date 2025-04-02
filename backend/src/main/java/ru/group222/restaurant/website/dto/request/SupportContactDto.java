package ru.group222.restaurant.website.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Schema(description = "Сущность для связи с тех. поддержкой")
public record SupportContactDto(
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

        @Schema(description = "Сообщение тех. поддержке", example = "any message")
        @NotBlank(message = "The given message must not be blank")
        String message
) {
}
