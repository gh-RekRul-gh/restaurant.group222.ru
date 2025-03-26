package ru.group222.restaurant.website.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import ru.group222.restaurant.website.dto.response.CategoryResponseDto;
import ru.group222.restaurant.website.mapping.CategoryMapper;
import ru.group222.restaurant.website.repository.CategoryRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
@Log4j2
public class CategoryService {
    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    public List<CategoryResponseDto> getCategories() {
        return categoryRepository.findAll().stream().map(
                categoryMapper::categoryToCategoryResponseDto
        ).toList();
    }
}
