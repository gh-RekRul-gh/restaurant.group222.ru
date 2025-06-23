package ru.group222.restaurant.website.mapping;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.group222.restaurant.website.config.MapstructConfig;
import ru.group222.restaurant.website.dto.request.OrderCreationDto;
import ru.group222.restaurant.website.dto.response.OrderItemResponseDto;
import ru.group222.restaurant.website.dto.response.OrderResponseDto;
import ru.group222.restaurant.website.model.Order;
import ru.group222.restaurant.website.model.OrderStatus;
import ru.group222.restaurant.website.model.User;

import java.time.Instant;
import java.util.List;

@Mapper(config = MapstructConfig.class, imports = {Instant.class})
public interface OrderMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", expression = "java(Instant.now())")
    @Mapping(target = "phoneNumber", source = "orderCreationDto.phoneNumber")
    Order orderCreationDtoToOrder(OrderCreationDto orderCreationDto, User user, OrderStatus status);

    @Mapping(target = "orderId", source = "order.id")
    @Mapping(target = "status", source = "order.status.name")
    OrderResponseDto orderToOrderResponseDto(Order order, List<OrderItemResponseDto> items);
}
