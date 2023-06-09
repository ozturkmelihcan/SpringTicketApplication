package com.melihcan.mapper;

import com.melihcan.dto.request.BuyTicketRequestDto;
import com.melihcan.dto.request.UpdateUserRequestDto;
import com.melihcan.rabbitmq.model.BuyTicketModel;
import com.melihcan.rabbitmq.model.CreateModel;
import com.melihcan.repository.entity.UserProfile;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IUserProfileMapper {

    IUserProfileMapper INSTANCE = Mappers.getMapper(IUserProfileMapper.class);

    UserProfile toUserProfile(final CreateModel createModel);


    UserProfile toUserProfile(final UpdateUserRequestDto updateUserRequestDto);
}
