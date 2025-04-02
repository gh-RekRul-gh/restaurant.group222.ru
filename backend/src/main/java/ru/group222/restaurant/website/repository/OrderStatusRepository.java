package ru.group222.restaurant.website.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.group222.restaurant.website.model.OrderStatus;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderStatusRepository extends CrudRepository<OrderStatus, Long> {
    Optional<OrderStatus> findByName(String name);

    @Override
    List<OrderStatus> findAll();
}
