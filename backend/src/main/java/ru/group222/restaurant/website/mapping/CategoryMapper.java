package ru.group222.restaurant.website.mapping;

import org.mapstruct.Mapper;
import ru.group222.restaurant.website.config.MapstructConfig;
import ru.group222.restaurant.website.dto.response.CategoryResponseDto;
import ru.group222.restaurant.website.model.Category;


@Mapper(config = MapstructConfig.class)
public interface CategoryMapper {
    CategoryResponseDto categoryToCategoryResponseDto(Category category);
}
