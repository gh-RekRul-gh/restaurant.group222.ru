package ru.group222.restaurant.website.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.group222.restaurant.website.dto.request.CartItemsAddDto;
import ru.group222.restaurant.website.dto.response.CartItemResponseDto;
import ru.group222.restaurant.website.dto.UserIdDto;
import ru.group222.restaurant.website.exception.CartItemOwnershipException;
import ru.group222.restaurant.website.exception.EntityNotFoundException;
import ru.group222.restaurant.website.exception.NotPositiveException;
import ru.group222.restaurant.website.mapping.CartItemMapper;
import ru.group222.restaurant.website.model.CartItem;
import ru.group222.restaurant.website.model.MenuItem;
import ru.group222.restaurant.website.model.User;
import ru.group222.restaurant.website.repository.CartItemRepository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.Queue;

@Service
@RequiredArgsConstructor
@Log4j2
public class CartItemService {
    private final CartItemRepository cartItemRepository;
    private final UserService userService;
    private final MenuService menuService;
    private final CartItemMapper cartItemMapper;

    private void validateCartItemOwnership(CartItem cartItem, User user) {
        if (!Objects.equals(cartItem.getUser().getId(), user.getId())) {
            String message = String.format("CartItem with id: %s is not owned by user with id: %s",
                    cartItem.getUser().getId(), user.getId());
            log.warn(message);
            throw new CartItemOwnershipException(message);
        }
    }

    private void validateCartItemQuantity(Integer quantity) {
        if (quantity <= 0) {
            String message = "Cart item quantity has to be positive";
            log.warn(message);
            throw new NotPositiveException(message);
        }
    }

    public CartItem findCartItemByIdOrElseThrow(Long id) {
        return cartItemRepository.findById(id)
                .orElseThrow(() -> {
                    String message = String.format("Cart item not found with id: %d", id);
                    log.warn(message);
                    return new EntityNotFoundException(message);
                });
    }

    public boolean isCartEmpty(User user) {
        return !cartItemRepository.existsByUserId(user.getId());
    }

    public List<CartItem> getUserCartItems(User user) {
        return cartItemRepository.findAllByUserId(user.getId());
    }

    public void clearUserCart(Long userId) {
        cartItemRepository.deleteAllByUserId(userId);

        log.info("Deleted all cart items for user with id: {}", userId);
    }

    public BigDecimal getCartPriceByUserId(Long userId) {
        List<CartItem> cartItems = cartItemRepository.findAllByUserId(userId);

        return cartItems.stream()
                .map(cartItem -> cartItem.getMenuItem().getPrice()
                        .multiply(BigDecimal.valueOf(cartItem.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);

    }

    @Transactional
    public void addCartItems(CartItemsAddDto itemsAddDto) {
        User user = userService.findUserByIdOrElseThrow(itemsAddDto.userId());

        itemsAddDto.items().forEach(cartItemAddDto -> {
            MenuItem menuItem = menuService.findMenuItemByIdOrElseThrow(cartItemAddDto.menuItemId());

            cartItemRepository.findByUserIdAndMenuItemId(user.getId(), menuItem.getId())
                    .ifPresentOrElse(
                            cartItem -> changeCartItemQuantity(cartItem, user, cartItemAddDto.quantity()),
                            () -> createCartItemWithQuantity(user, menuItem, cartItemAddDto.quantity())
                    );
        });
    }

    private void changeCartItemQuantity(CartItem cartItem, User user, Integer additionalQuantity) {
        Integer newQuantity = cartItem.getQuantity() + additionalQuantity;
        validateCartItemQuantity(newQuantity);
        cartItem.setQuantity(newQuantity);
        cartItemRepository.save(cartItem);
        log.info("Cart item quantity was updated with id: {} for user with id: {}", cartItem.getId(), user.getId());
    }

    private void createCartItemWithQuantity(User user, MenuItem menuItem, Integer quantity) {
        validateCartItemQuantity(quantity);
        CartItem item = cartItemMapper.cartItemAddDtoToItem(quantity, user, menuItem);
        cartItemRepository.save(item);
        log.info("New item was added with id: {} for user with id: {}", item.getId(), user.getId());
    }

    @Transactional
    public void deleteCartItem(UserIdDto userIdDto, Long cartItemId) {
        User user = userService.findUserByIdOrElseThrow(userIdDto.userId());
        CartItem cartItem = findCartItemByIdOrElseThrow(cartItemId);

        validateCartItemOwnership(cartItem, user);

        cartItemRepository.delete(cartItem);

        log.info("Deleted cart item with id: {}", cartItemId);
    }

    @Transactional
    public void emptyCart(UserIdDto userIdDto) {
        User user = userService.findUserByIdOrElseThrow(userIdDto.userId());
        clearUserCart(user.getId());
    }

    public List<CartItemResponseDto> getCart(Long userId) {
        User user = userService.findUserByIdOrElseThrow(userId);

        return getUserCartItems(user).stream().map(
                cartItemMapper::cartItemToCartItemResponseDto
        ).toList();
    }
}

// package ru.group222.restaurant.website.service;

// import lombok.RequiredArgsConstructor;
// import lombok.extern.log4j.Log4j2;
// import org.springframework.stereotype.Service;
// import org.springframework.transaction.annotation.Transactional;
// import ru.group222.restaurant.website.dto.request.CartItemsAddDto;
// import ru.group222.restaurant.website.dto.response.CartItemResponseDto;
// import ru.group222.restaurant.website.dto.UserIdDto;
// import ru.group222.restaurant.website.exception.CartItemOwnershipException;
// import ru.group222.restaurant.website.exception.EntityNotFoundException;
// import ru.group222.restaurant.website.exception.NotPositiveException;
// import ru.group222.restaurant.website.mapping.CartItemMapper;
// import ru.group222.restaurant.website.model.CartItem;
// import ru.group222.restaurant.website.model.MenuItem;
// import ru.group222.restaurant.website.model.User;
// import ru.group222.restaurant.website.repository.CartItemRepository;

// import java.math.BigDecimal;
// import java.util.List;
// import java.util.Objects;

// @Service
// @RequiredArgsConstructor
// @Log4j2
// public class CartItemService {
//     private final CartItemRepository cartItemRepository;
//     private final UserService userService;
//     private final MenuService menuService;
//     private final CartItemMapper cartItemMapper;

//     private void validateCartItemOwnership(CartItem cartItem, User user) {
//         if (!Objects.equals(cartItem.getUser().getId(), user.getId())) {
//             String message = String.format("CartItem with id: %s is not owned by user with id: %s",
//                     cartItem.getUser().getId(), user.getId());
//             log.warn(message);
//             throw new CartItemOwnershipException(message);
//         }
//     }

//     private void validateCartItemQuantity(Integer quantity) {
//         if (quantity < 0) {
//             String message = "Cart item quantity has to be positive";
//             log.warn(message);
//             throw new NotPositiveException(message);
//         }
//     }

//     public CartItem findCartItemByIdOrElseThrow(Long id) {
//         return cartItemRepository.findById(id)
//                 .orElseThrow(() -> {
//                     String message = String.format("Cart item not found with id: %d", id);
//                     log.warn(message);
//                     return new EntityNotFoundException(message);
//                 });
//     }

//     public boolean isCartEmpty(User user) {
//         return !cartItemRepository.existsByUserId(user.getId());
//     }

//     public List<CartItem> getUserCartItems(User user) {
//         return cartItemRepository.findAllByUserId(user.getId());
//     }

//     public void clearUserCart(Long userId) {
//         cartItemRepository.deleteAllByUserId(userId);

//         log.info("Deleted all cart items for user with id: {}", userId);
//     }

//     public BigDecimal getCartPriceByUserId(Long userId) {
//         List<CartItem> cartItems = cartItemRepository.findAllByUserId(userId);

//         return cartItems.stream()
//                 .map(cartItem -> cartItem.getMenuItem().getPrice()
//                         .multiply(BigDecimal.valueOf(cartItem.getQuantity())))
//                 .reduce(BigDecimal.ZERO, BigDecimal::add);

//     }

//     @Transactional
//     public void addCartItems(CartItemsAddDto itemsAddDto) {
//         User user = userService.findUserByIdOrElseThrow(itemsAddDto.userId());

//         itemsAddDto.items().forEach(cartItemAddDto -> {
//             validateCartItemQuantity(cartItemAddDto.quantity());

//             MenuItem menuItem = menuService.findMenuItemByIdOrElseThrow(cartItemAddDto.menuItemId());

//             cartItemRepository.findByUserIdAndMenuItemId(user.getId(), menuItem.getId())
//                     .ifPresentOrElse(
//                             cartItem -> {
//                                 cartItem.setQuantity(cartItem.getQuantity() + cartItemAddDto.quantity());
//                                 cartItemRepository.save(cartItem);
//                                 log.info("Cart item quantity was updated with id: {} for user with id: {}",
//                                         cartItem.getId(), user.getId());
//                             },
//                             () -> {
//                                 CartItem item = cartItemMapper.cartItemAddDtoToItem(cartItemAddDto.quantity(), user, menuItem);
//                                 cartItemRepository.save(item);
//                                 log.info("New item was added with id: {} for user with id: {}", item.getId(), user.getId());
//                             }
//                     );
//         });
//     }

//     @Transactional
//     public void deleteCartItem(UserIdDto userIdDto, Long cartItemId) {
//         User user = userService.findUserByIdOrElseThrow(userIdDto.userId());
//         CartItem cartItem = findCartItemByIdOrElseThrow(cartItemId);

//         validateCartItemOwnership(cartItem, user);

//         cartItemRepository.delete(cartItem);

//         log.info("Deleted cart item with id: {}", cartItemId);
//     }

//     @Transactional
//     public void emptyCart(UserIdDto userIdDto) {
//         User user = userService.findUserByIdOrElseThrow(userIdDto.userId());
//         clearUserCart(user.getId());
//     }

//     public List<CartItemResponseDto> getCart(Long userId) {
//         User user = userService.findUserByIdOrElseThrow(userId);

//         return getUserCartItems(user).stream().map(
//                 cartItemMapper::cartItemToCartItemResponseDto
//         ).toList();
//     }
// }
