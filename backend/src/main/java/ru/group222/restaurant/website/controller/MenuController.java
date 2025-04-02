package ru.group222.restaurant.website.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.group222.restaurant.website.dto.response.ApiResponseDto;
import ru.group222.restaurant.website.dto.response.MenuItemResponseDto;
import ru.group222.restaurant.website.service.MenuService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/menu")
@Log4j2
public class MenuController {
    private final MenuService menuService;

    @GetMapping
    @Operation(summary = "Получение всего меню")
    ResponseEntity<ApiResponseDto<List<MenuItemResponseDto>>> getMenu() {
        List<MenuItemResponseDto> menu = menuService.getMenu();

        log.info("Getting menu succeed");

        return ResponseEntity.ok(ApiResponseDto.success(menu));
    }

    @GetMapping("/{menuItemId}")
    @Operation(summary = "Получение блюда по id")
    ResponseEntity<ApiResponseDto<MenuItemResponseDto>> getMenuItem(
            @PathVariable @Parameter(description = "Id блюда в меню", example = "1") Long menuItemId
    ) {
        MenuItemResponseDto menuItem = menuService.getMenuItem(menuItemId);

        log.info("Getting menu succeed item with id: {}", menuItemId);

        return ResponseEntity.ok(ApiResponseDto.success(menuItem));
    }
}
