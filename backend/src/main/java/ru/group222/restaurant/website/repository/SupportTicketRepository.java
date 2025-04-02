package ru.group222.restaurant.website.repository;

import org.springframework.data.repository.CrudRepository;
import ru.group222.restaurant.website.model.SupportTicket;

public interface SupportTicketRepository extends CrudRepository<SupportTicket, Long> {
}
