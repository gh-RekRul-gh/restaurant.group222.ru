package ru.group222.restaurant.website.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.group222.restaurant.website.model.Order;

@Repository
public interface OrderRepository extends CrudRepository<Order, Long> {
}
