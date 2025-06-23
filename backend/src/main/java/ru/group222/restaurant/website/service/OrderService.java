package ru.group222.restaurant.website.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.group222.restaurant.website.dto.request.OrderCreationDto;
import ru.group222.restaurant.website.dto.request.OrderStatusChangingDto;
import ru.group222.restaurant.website.dto.response.OrderItemResponseDto;
import ru.group222.restaurant.website.dto.response.OrderResponseDto;
import ru.group222.restaurant.website.exception.EntityNotFoundException;
import ru.group222.restaurant.website.exception.OrderCreationWithEmptyCartException;
import ru.group222.restaurant.website.exception.OrderStatusInappropriateException;
import ru.group222.restaurant.website.exception.OrderTotalPriceMismatchException;
import ru.group222.restaurant.website.mapping.OrderItemMapper;
import ru.group222.restaurant.website.mapping.OrderMapper;
import ru.group222.restaurant.website.model.CartItem;
import ru.group222.restaurant.website.model.Order;
import ru.group222.restaurant.website.model.OrderItem;
import ru.group222.restaurant.website.model.OrderStatus;
import ru.group222.restaurant.website.model.User;
import ru.group222.restaurant.website.repository.OrderRepository;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
@Log4j2
public class OrderService {
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final OrderItemMapper orderItemMapper;
    private final UserService userService;
    private final CartItemService cartItemService;
    private final OrderStatusService orderStatusService;
    private final OrderItemService orderItemService;
    private final EmailService emailService;

    public static final BigDecimal ORDER_DELIVERY_PRICE = BigDecimal.valueOf(500);

    public Order findOrderByIdOrElseThrow(Long id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> {
                    String message = String.format("Order not found with id: %d", id);
                    log.warn(message);
                    return new EntityNotFoundException(message);
                });
    }

    private void validateOrderTotalPrice(User user, BigDecimal totalPrice) {
        BigDecimal countedTotalPrice = cartItemService.getCartPriceByUserId(user.getId()).add(ORDER_DELIVERY_PRICE);

        if (countedTotalPrice.compareTo(totalPrice) != 0) {
            String message = String.format(
                    "Total price does not match the calculated value. Expected: %s, received: %s",
                    countedTotalPrice, totalPrice
            );
            log.warn(message);
            throw new OrderTotalPriceMismatchException(message);
        }
    }

    private void validateCartEmptiness(User user) {
        if (cartItemService.isCartEmpty(user)) {
            String message = String.format("Can not create an order with empty cart for user with id: %d", user.getId());
            log.warn(message);
            throw new OrderCreationWithEmptyCartException(message);
        }
    }

    @Transactional
    public void createOrder(OrderCreationDto orderCreationDto) {
        User user = userService.findUserByIdOrElseThrow(orderCreationDto.userId());

        validateCartEmptiness(user);

        validateOrderTotalPrice(user, orderCreationDto.totalPrice());

        OrderStatus orderStatus = orderStatusService.findOrderStatusByNameOrElseThrow("Подтвержден");

        Order newOrder = orderMapper.orderCreationDtoToOrder(orderCreationDto, user, orderStatus);
        orderRepository.save(newOrder);

        List<CartItem> cartItems = cartItemService.getUserCartItems(user);
        orderItemService.putCartItemsToOrderItems(cartItems, newOrder, user);

        cartItemService.clearUserCart(user.getId());

        String emailMessage = String.format(EmailService.ORDER_CREATED_MESSAGE, user.getName(), newOrder.getId());
        emailService.sendEmail(user.getEmail(), emailMessage, "Order creation");

        log.info("Successfully created order with id: {} for user with id: {}", newOrder.getId(), user.getId());
    }

    @Transactional
    public void changeOrderStatus(OrderStatusChangingDto orderStatusChangingDto) {
        Order order = findOrderByIdOrElseThrow(orderStatusChangingDto.orderId());
        OrderStatus status = orderStatusService.findOrderStatusByNameOrElseThrow(orderStatusChangingDto.statusName());

        order.setStatus(status);
        orderRepository.save(order);

        log.info("Success changing status for order with id: {} to: {}", order.getId(), status.getName());
    }

    private OrderResponseDto getOrderResponseDtoFromOrder(Order order) {
        List<OrderItem> orderItems = orderItemService.getOrderItemsForOrder(order);
        List<OrderItemResponseDto> orderItemResponseDtos = orderItems.stream().map(
                orderItemMapper::orderItemToOrderItemResponseDto
        ).toList();

        return orderMapper.orderToOrderResponseDto(order, orderItemResponseDtos);
    }

    public OrderResponseDto getOrder(Long orderId) {
        Order order = findOrderByIdOrElseThrow(orderId);
        OrderResponseDto orderResponseDto = getOrderResponseDtoFromOrder(order);

        log.info("Success getting order with id: {}", order.getId());

        return orderResponseDto;
    }

    public List<String> getOrderStatuses() {
        return orderStatusService.getAllStatuses().stream().map(
                OrderStatus::getName
        ).toList();
    }

    private List<Order> getOrdersWithStatus(Long userId, String status) {
        List<Order> orders;
        switch (status) {
            case null -> orders = orderRepository.findAllByUserId(userId);

            case ("active") -> orders = orderRepository.findActiveByUserId(userId);

            case ("completed") -> {
                OrderStatus orderStatus = orderStatusService.findOrderStatusByNameOrElseThrow("Доставлен");
                orders = orderRepository.findAllByUserIdAndStatus(userId, orderStatus);
            }

            default -> {
                String message = String.format("Status '%s' is inappropriate for getting orders list", status);
                log.warn(message);
                throw new OrderStatusInappropriateException(message);
            }
        }
        return orders;
    }

    public List<OrderResponseDto> getUserOrders(Long userId, String status) {
        User user = userService.findUserByIdOrElseThrow(userId);

        List<Order> orders = getOrdersWithStatus(user.getId(), status);

        List<OrderResponseDto> orderResponseDtos = orders.stream().map(
                this::getOrderResponseDtoFromOrder
        ).toList();

        log.info("Success getting order for user with id: {} and status: {}", userId, status);

        return orderResponseDtos;
    }
}
