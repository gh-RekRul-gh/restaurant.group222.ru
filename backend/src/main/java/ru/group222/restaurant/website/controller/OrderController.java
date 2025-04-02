package ru.group222.restaurant.website.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.group222.restaurant.website.dto.request.OrderCreationDto;
import ru.group222.restaurant.website.dto.request.OrderStatusChangingDto;
import ru.group222.restaurant.website.dto.response.ApiResponseDto;
import ru.group222.restaurant.website.dto.response.OrderResponseDto;
import ru.group222.restaurant.website.service.OrderService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class OrderController {
    private final OrderService orderService;

    @PostMapping("/order")
    @Operation(summary = "Создание заказа")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400", description = "Корзина пользователя пуста"),
            @ApiResponse(responseCode = "404", description = "Пользователь не найден")
    })
    ResponseEntity<ApiResponseDto<Void>> createOrder(@Valid @RequestBody OrderCreationDto orderCreationDto) {
        orderService.createOrder(orderCreationDto);
        return ResponseEntity.ok(ApiResponseDto.success());
    }

    @PatchMapping("/order/status")
    @Operation(summary = "Смена статуса заказа")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "404", description = "Корзина или статус не найден")
    })
    ResponseEntity<ApiResponseDto<Void>> changeOrderStatus(@Valid @RequestBody OrderStatusChangingDto orderStatusChangingDto) {
        orderService.changeOrderStatus(orderStatusChangingDto);
        return ResponseEntity.ok(ApiResponseDto.success());
    }

    @GetMapping("/order/{orderId}")
    @Operation(summary = "Получение заказа по id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "404", description = "Заказ не найден")
    })
    ResponseEntity<ApiResponseDto<OrderResponseDto>> getOrder(@PathVariable Long orderId) {
        OrderResponseDto orderResponseDto = orderService.getOrder(orderId);
        return ResponseEntity.ok(ApiResponseDto.success(orderResponseDto));
    }

    @GetMapping("/order/statuses")
    @Operation(summary = "Получение всех статусов заказов")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
    })
    ResponseEntity<ApiResponseDto<List<String>>> getOrderStatuses() {
        List<String> orderStatusesNames = orderService.getOrderStatuses();
        return ResponseEntity.ok(ApiResponseDto.success(orderStatusesNames));
    }

    @GetMapping("/user/{userId}/orders")
    @Operation(summary = "Получение заказов пользователя")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400", description = "Неверный параметр статуса получаемых заказов"),
            @ApiResponse(responseCode = "404", description = "Пользователь не найден")
    })
    ResponseEntity<ApiResponseDto<List<OrderResponseDto>>> getUserOrders(
            @PathVariable @Parameter(description = "Id пользователя", example = "1") Long userId,
            @RequestParam(required = false) @Parameter(example = "active", description = """
                    Статус получаемых заказов:
                    ничего - для всех заказов пользователей;
                    active - для активных заказов пользователя;
                    completed - для завершенных заказов пользователя.""") String status
    ) {
        List<OrderResponseDto> orderResponseDtos = orderService.getUserOrders(userId, status);
        return ResponseEntity.ok(ApiResponseDto.success(orderResponseDtos));
    }
}
