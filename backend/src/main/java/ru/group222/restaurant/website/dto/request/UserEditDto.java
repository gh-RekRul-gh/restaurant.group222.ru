package ru.group222.restaurant.website.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Schema(description = "Сущность для редактирования профиля пользователя")
public record UserEditDto(
        @Schema(description = "Id пользователя", example = "1")
        @NotNull(message = "User id must not be null")
        Long userId,

        @Schema(description = "Имя пользователя", example = "any_string")
        @NotBlank(message = "The given name must not be blank")
        @Size(max = 100, message = "User's name maximum length is 100 symbols")
        String name,

        @Schema(description = "Номер телефона пользователя", example = "+79998887766")
        @NotBlank(message = "The given phone number must not be blank")
        @Pattern(regexp = "\\+7(\\d{10})", message = "Invalid phone number")
        String phoneNumber
) {
}
