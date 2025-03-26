package ru.group222.restaurant.website.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.group222.restaurant.website.model.MenuItem;

import java.util.List;

@Repository
public interface MenuItemRepository extends CrudRepository<MenuItem, Long> {
    @Override
    List<MenuItem> findAll();
}
