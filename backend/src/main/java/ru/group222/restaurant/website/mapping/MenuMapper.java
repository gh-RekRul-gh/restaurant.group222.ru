package ru.group222.restaurant.website.mapping;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.group222.restaurant.website.config.MapstructConfig;
import ru.group222.restaurant.website.dto.response.MenuItemResponseDto;
import ru.group222.restaurant.website.model.MenuItem;

@Mapper(config = MapstructConfig.class)
public interface MenuMapper {
    @Mapping(target = "categoryId", expression = "java(menuItem.getCategory().getId())")
    @Mapping(target = "menuItemId", source = "id")
    MenuItemResponseDto menuToMenuItemResponseDto(MenuItem menuItem);
}
