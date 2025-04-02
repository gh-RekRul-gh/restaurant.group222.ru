package ru.group222.restaurant.website.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.group222.restaurant.website.mapping.OrderItemMapper;
import ru.group222.restaurant.website.model.CartItem;
import ru.group222.restaurant.website.model.Order;
import ru.group222.restaurant.website.model.OrderItem;
import ru.group222.restaurant.website.model.User;
import ru.group222.restaurant.website.repository.OrderItemRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
@Log4j2
public class OrderItemService {
    private final OrderItemRepository orderItemRepository;
    private final OrderItemMapper orderItemMapper;

    public List<OrderItem> getOrderItemsForOrder(Order order) {
        return orderItemRepository.findAllByOrderId(order.getId());
    }

    @Transactional
    public void putCartItemsToOrderItems(List<CartItem> cartItems, Order order, User user) {
        List<OrderItem> orderItems = cartItems.stream()
                .map(cartItem -> orderItemMapper.cartItemToOrderItem(cartItem, order, user))
                .toList();

        orderItemRepository.saveAll(orderItems);

        log.info("Cart items were put to order with id: {} for user with id: {}", order.getId(), user.getId());
    }
}
