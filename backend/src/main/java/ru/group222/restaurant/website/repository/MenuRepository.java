package ru.group222.restaurant.website.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.group222.restaurant.website.model.Menu;

import java.util.List;

@Repository
public interface MenuRepository extends CrudRepository<Menu, Long> {
    @Override
    List<Menu> findAll();
}
