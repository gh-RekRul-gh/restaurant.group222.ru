package ru.group222.restaurant.website.mapping;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.group222.restaurant.website.config.MapstructConfig;
import ru.group222.restaurant.website.dto.request.SupportContactDto;
import ru.group222.restaurant.website.model.SupportTicket;

import java.time.Instant;

@Mapper(config = MapstructConfig.class, imports = {Instant.class})
public interface SupportTicketMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", expression = "java(java.time.Instant.now())")
    @Mapping(target = "status", constant = "OPEN")
    @Mapping(target = "customerName", source = "name")
    SupportTicket supportContactDtoToSupportTicket(SupportContactDto supportContactDto);
}
