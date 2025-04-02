package ru.group222.restaurant.website.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.group222.restaurant.website.model.Order;
import ru.group222.restaurant.website.model.OrderStatus;

import java.util.List;

@Repository
public interface OrderRepository extends CrudRepository<Order, Long> {
    List<Order> findAllByUserId(Long userId);

    List<Order> findAllByUserIdAndStatus(Long userId, OrderStatus orderStatus);

    @Query("SELECT o FROM Order o WHERE o.user.id = :userId AND o.status.name != 'Доставлен'")
    List<Order> findActiveByUserId(@Param("userId") Long userId);
}
