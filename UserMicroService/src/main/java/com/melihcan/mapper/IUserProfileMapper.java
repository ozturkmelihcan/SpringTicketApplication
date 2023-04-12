package com.melihcan.mapper;

import com.melihcan.dto.request.BuyTicketRequestDto;
import com.melihcan.rabbitmq.model.BuyTicketModel;
import com.melihcan.rabbitmq.model.CreateUserModel;
import com.melihcan.repository.entity.UserProfile;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IUserProfileMapper {

    IUserProfileMapper INSTANCE = Mappers.getMapper(IUserProfileMapper.class);

    UserProfile toUserProfile(final CreateUserModel createUserModel);

    BuyTicketModel toBuyTicketModel(final BuyTicketRequestDto buyTicketRequestDto);
}
