package ru.group222.restaurant.website.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Сущность категории")
public record CategoryResponseDto(
        @Schema(description = "Id категории", example = "1")
        Long categoryId,

        @Schema(description = "Название категории", example = "Салаты")
        String name
) {
}
