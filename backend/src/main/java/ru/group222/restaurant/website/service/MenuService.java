package ru.group222.restaurant.website.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import ru.group222.restaurant.website.dto.response.MenuItemResponseDto;
import ru.group222.restaurant.website.exception.EntityNotFoundException;
import ru.group222.restaurant.website.mapping.MenuMapper;
import ru.group222.restaurant.website.model.MenuItem;
import ru.group222.restaurant.website.repository.MenuItemRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
@Log4j2
public class MenuService {
    private final MenuItemRepository menuItemRepository;
    private final MenuMapper menuMapper;

    public MenuItem findMenuItemByIdOrElseThrow(Long id) {
        return menuItemRepository.findById(id)
                .orElseThrow(() -> {
                    String message = String.format("Menu item not found with id: %d", id);
                    log.warn(message);
                    return new EntityNotFoundException(message);
                });
    }

    public List<MenuItemResponseDto> getMenu() {
        return menuItemRepository.findAll().stream().map(
                menuMapper::menuToMenuItemResponseDto
        ).toList();
    }

    public MenuItemResponseDto getMenuItem(Long id) {
        MenuItem menuItem = findMenuItemByIdOrElseThrow(id);
        return menuMapper.menuToMenuItemResponseDto(menuItem);
    }
}
