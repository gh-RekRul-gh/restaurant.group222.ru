package ru.group222.restaurant.website.mapping;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.group222.restaurant.website.config.MapstructConfig;
import ru.group222.restaurant.website.dto.response.CategoryResponseDto;
import ru.group222.restaurant.website.model.Category;


@Mapper(config = MapstructConfig.class)
public interface CategoryMapper {
    @Mapping(target = "categoryId", source = "id")
    CategoryResponseDto categoryToCategoryResponseDto(Category category);
}
