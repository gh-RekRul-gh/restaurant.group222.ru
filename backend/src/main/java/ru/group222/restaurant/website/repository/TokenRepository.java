package ru.group222.restaurant.website.repository;

import org.springframework.data.repository.CrudRepository;
import ru.group222.restaurant.website.model.Token;

import java.util.Optional;

public interface TokenRepository extends CrudRepository<Token, Long> {
    Optional<Token> findByCode(String code);

    boolean existsByCode(String code);
}
