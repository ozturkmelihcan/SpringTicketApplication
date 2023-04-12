package com.melihcan.service;

import com.melihcan.dto.request.BuyTicketRequestDto;
import com.melihcan.dto.request.UpdateUserRequestDto;
import com.melihcan.exception.EErrorType;
import com.melihcan.exception.UserException;
import com.melihcan.mapper.IUserProfileMapper;
import com.melihcan.rabbitmq.model.BuyTicketModel;
import com.melihcan.rabbitmq.model.CreateUserModel;
import com.melihcan.rabbitmq.producer.BuyTicketProducer;
import com.melihcan.repository.IUserProfileRepository;
import com.melihcan.repository.entity.UserProfile;
import com.melihcan.utility.JwtTokenManager;
import com.melihcan.utility.ServiceManager;

import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserProfileService extends ServiceManager<UserProfile,Long> {

    private final IUserProfileRepository userProfileRepository;

    private final JwtTokenManager jwtTokenManager;

    private final BuyTicketProducer buyTicketProducer;
    public UserProfileService(IUserProfileRepository userProfileRepository, JwtTokenManager jwtTokenManager, BuyTicketProducer buyTicketProducer){
        super(userProfileRepository);
        this.userProfileRepository=userProfileRepository;
        this.jwtTokenManager=jwtTokenManager;
        this.buyTicketProducer = buyTicketProducer;
    }



    public void save(CreateUserModel createUserModel){
        // KONTROLLER YAPILACAK
        UserProfile userProfile = IUserProfileMapper.INSTANCE.toUserProfile(createUserModel);
        save(userProfile);
    }

    public Boolean updateDto(UpdateUserRequestDto dto) {
        Optional<Long>authId =  jwtTokenManager.getIdFromToken(dto.getToken());
        System.out.println(authId);
        if (authId.isEmpty()) throw new UserException(EErrorType.INVALID_TOKEN);
        Optional<UserProfile> userProfile = userProfileRepository.findOptionalByAuthid(authId.get());
        // MAPPERLA YAPILACAK
        userProfile.get().setEmail(dto.getEmail());
        userProfile.get().setPhone(dto.getPhone());
        userProfile.get().setAbout(dto.getAbout());
        userProfile.get().setAddress(dto.getAddress());
        update(userProfile.get());
        return true;
    }

    public Boolean buyTicket(BuyTicketRequestDto buyTicketRequestDto) {
        Optional<Long>userId = jwtTokenManager.getIdFromToken(buyTicketRequestDto.getToken());
        if (userId.isEmpty())throw new UserException(EErrorType.INVALID_TOKEN);
        BuyTicketModel buyTicketModel = new BuyTicketModel();
        buyTicketModel.setUserid(userId.get());
        buyTicketModel.setTime(buyTicketRequestDto.getTime());
        buyTicketModel.setKoltukno(buyTicketRequestDto.getKoltukno());
        System.out.println(userId.get());
        System.out.println(buyTicketRequestDto.getTime());
        System.out.println(buyTicketRequestDto.getKoltukno());
        buyTicketProducer.createSendMessage(buyTicketModel);
        return true;


    }
}
