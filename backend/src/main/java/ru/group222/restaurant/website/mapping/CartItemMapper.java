package ru.group222.restaurant.website.mapping;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.group222.restaurant.website.config.MapstructConfig;
import ru.group222.restaurant.website.dto.response.CartItemResponseDto;
import ru.group222.restaurant.website.model.CartItem;
import ru.group222.restaurant.website.model.MenuItem;
import ru.group222.restaurant.website.model.User;

@Mapper(config = MapstructConfig.class)
public interface CartItemMapper {
    @Mapping(target = "id", ignore = true)
    CartItem cartItemAddDtoToItem(Integer quantity, User user, MenuItem menuItem);

    @Mapping(target = "cartItemId", source = "cartItem.id")
    @Mapping(target = "quantity", source = "cartItem.quantity")
    @Mapping(target = "menuItemId", expression = "java(cartItem.getMenuItem().getId())")
    @Mapping(target = "name", expression = "java(cartItem.getMenuItem().getName())")
    @Mapping(target = "price", expression = "java(cartItem.getMenuItem().getPrice())")
    @Mapping(target = "imageUrl", expression = "java(cartItem.getMenuItem().getImageUrl())")
    CartItemResponseDto cartItemToCartItemResponseDto(CartItem cartItem);
}
