package ru.group222.restaurant.website.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.group222.restaurant.website.model.CartItem;

import java.util.List;
import java.util.Optional;

@Repository
public interface CartItemRepository extends CrudRepository<CartItem, Long> {
    Optional<CartItem> findByUserIdAndMenuItemId(Long userId, Long menuItemId);

    boolean existsByUserId(Long userId);

    @Modifying
    @Query("DELETE FROM CartItem c WHERE c.user.id = :userId")
    void deleteAllByUserId(@Param("userId") Long userId);

    List<CartItem> findAllByUserId(Long userId);
}
