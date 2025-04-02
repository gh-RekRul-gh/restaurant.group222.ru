package ru.group222.restaurant.website.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import ru.group222.restaurant.website.exception.EntityNotFoundException;
import ru.group222.restaurant.website.model.OrderStatus;
import ru.group222.restaurant.website.repository.OrderStatusRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
@Log4j2
public class OrderStatusService {
    private final OrderStatusRepository orderStatusRepository;

    public OrderStatus findOrderStatusByNameOrElseThrow(String name) {
        return orderStatusRepository.findByName(name)
                .orElseThrow(() -> {
                    String message = String.format("OrderStatus not found with name: %s", name);
                    log.warn(message);
                    return new EntityNotFoundException(message);
                });
    }

    List<OrderStatus> getAllStatuses() {
        return orderStatusRepository.findAll();
    }
}
