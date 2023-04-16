package com.melihcan.mapper;

import com.melihcan.dto.request.TicketForUserRequestDto;
import com.melihcan.rabbitmq.model.SaleTicketModel;
import com.melihcan.repository.entity.Ticket;
import com.melihcan.repository.entity.TicketForUser;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ITicketMapper {

    ITicketMapper INSTANCE = Mappers.getMapper(ITicketMapper.class);

    Ticket toTicket(final SaleTicketModel saleTicketModel);

    TicketForUser toTicketForUser(final TicketForUserRequestDto ticketForUserRequestDto);


}
