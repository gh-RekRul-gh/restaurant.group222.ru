package ru.group222.restaurant.website.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.group222.restaurant.website.dto.request.CartItemsAddDto;
import ru.group222.restaurant.website.dto.response.ApiResponseDto;
import ru.group222.restaurant.website.dto.response.CartItemResponseDto;
import ru.group222.restaurant.website.dto.UserIdDto;
import ru.group222.restaurant.website.service.CartItemService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Log4j2
@RequestMapping("/api/v1")
public class CartItemController {
    private final CartItemService cartItemService;

    @PostMapping("/cart/items")
    @Operation(summary = "Добавление блюд в корзину")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400", description = "Не положительное количество позиции"),
            @ApiResponse(responseCode = "404", description = "Пользователь или блюдо не найдено")
    })
    ResponseEntity<ApiResponseDto<Void>> addCartItems(@Valid @RequestBody CartItemsAddDto cartItemsAddDto) {
        cartItemService.addCartItems(cartItemsAddDto);
        return ResponseEntity.ok(ApiResponseDto.success());
    }

    @DeleteMapping("/cart/items/{cartItemId}")
    @Operation(summary = "Удаление блюда из корзины")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "403", description = "Удаляемый товар принадлежит другому пользователю"),
            @ApiResponse(responseCode = "404", description = "Пользователь или элемент корзины не найден")
    })
    ResponseEntity<ApiResponseDto<Void>> deleteCartItem(
            @Valid @RequestBody UserIdDto userIdDto,
            @PathVariable @Parameter(description = "Id позиции в корзине", example = "1") Long cartItemId
    ) {
        cartItemService.deleteCartItem(userIdDto, cartItemId);
        return ResponseEntity.ok(ApiResponseDto.success());
    }

    @DeleteMapping("/cart")
    @Operation(summary = "Очистка корзины пользователя")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "404", description = "Пользователь не найден")
    })
    ResponseEntity<ApiResponseDto<Void>> emptyCart(@Valid @RequestBody UserIdDto userIdDto) {
        cartItemService.emptyCart(userIdDto);
        return ResponseEntity.ok(ApiResponseDto.success());
    }

    @GetMapping("/user/{userId}/cart")
    @Operation(summary = "Получение корзины пользователя")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "404", description = "Пользователь или товар не найден")
    })
    ResponseEntity<ApiResponseDto<List<CartItemResponseDto>>> getCart(
            @PathVariable @Parameter(description = "Id пользователя", example = "1") Long userId
    ) {
        List<CartItemResponseDto> cart = cartItemService.getCart(userId);

        log.info("Getting cart succeed for user with id: {}", userId);

        return ResponseEntity.ok(ApiResponseDto.success(cart));
    }
}
