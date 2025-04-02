package ru.group222.restaurant.website.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.group222.restaurant.website.dto.response.ApiResponseDto;
import ru.group222.restaurant.website.dto.response.CategoryResponseDto;
import ru.group222.restaurant.website.service.CategoryService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
@Log4j2
public class CategoryController {
    private final CategoryService categoryService;

    @GetMapping("/categories")
    @Operation(summary = "Получение всех категорий блюд")
    ResponseEntity<ApiResponseDto<List<CategoryResponseDto>>> getCategories() {
        List<CategoryResponseDto> categories = categoryService.getCategories();

        log.info("Getting categories succeed");

        return ResponseEntity.ok(ApiResponseDto.success(categories));
    }
}
