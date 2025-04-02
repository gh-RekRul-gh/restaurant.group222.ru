package ru.group222.restaurant.website.mapping;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.group222.restaurant.website.config.MapstructConfig;
import ru.group222.restaurant.website.dto.response.OrderItemResponseDto;
import ru.group222.restaurant.website.model.CartItem;
import ru.group222.restaurant.website.model.Order;
import ru.group222.restaurant.website.model.OrderItem;
import ru.group222.restaurant.website.model.User;

@Mapper(config = MapstructConfig.class)
public interface OrderItemMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "user", source = "user")
    @Mapping(target = "menuItem", source = "cartItem.menuItem")
    @Mapping(target = "quantity", source = "cartItem.quantity")
    @Mapping(target = "order", source = "order")
    @Mapping(target = "unitPrice", source = "cartItem.menuItem.price")
    OrderItem cartItemToOrderItem(CartItem cartItem, Order order, User user);

    @Mapping(target = "price", source = "orderItem.unitPrice")
    @Mapping(target = "menuItemId", source = "orderItem.menuItem.id")
    @Mapping(target = "name", source = "orderItem.menuItem.name")
    @Mapping(target = "imageUrl", source = "orderItem.menuItem.imageUrl")
    OrderItemResponseDto orderItemToOrderItemResponseDto(OrderItem orderItem);
}
