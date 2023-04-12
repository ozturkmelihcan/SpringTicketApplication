package com.melihcan.mapper;

import com.melihcan.dto.request.CreateTicketRequestDto;
import com.melihcan.dto.request.TicketSalesRequestDto;
import com.melihcan.rabbitmq.model.BuyTicketModel;
import com.melihcan.repository.entity.Ticket;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ITicketMapper {

    ITicketMapper INSTANCE = Mappers.getMapper(ITicketMapper.class);

//    Ticket toTicket(final BuyTicketModel buyTicketModel);

//    @Mapping(source = "ticketid",target = "id")
//    Ticket toTicket(final TicketSalesRequestDto dto);
}
