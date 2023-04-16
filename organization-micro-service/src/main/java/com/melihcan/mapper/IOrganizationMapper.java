package com.melihcan.mapper;

import com.melihcan.dto.request.SaleTicketRequestDto;
import com.melihcan.dto.request.UpdateOrganizationRequestDto;
import com.melihcan.rabbitmq.model.CreateModel;
import com.melihcan.rabbitmq.model.SaleTicketModel;
import com.melihcan.repository.entity.Organization;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IOrganizationMapper {

    IOrganizationMapper INSTANCE = Mappers.getMapper(IOrganizationMapper.class);

    Organization toOrganization(final CreateModel createModel);


    SaleTicketModel toSaleTicketModel(final SaleTicketRequestDto saleTicketRequestDto);
}
