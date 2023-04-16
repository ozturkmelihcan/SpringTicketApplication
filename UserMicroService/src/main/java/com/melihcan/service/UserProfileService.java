package com.melihcan.service;

import com.melihcan.dto.request.BuyTicketRequestDto;
import com.melihcan.dto.request.UpdateUserRequestDto;
import com.melihcan.exception.EErrorType;
import com.melihcan.exception.UserException;
import com.melihcan.mapper.IUserProfileMapper;
import com.melihcan.rabbitmq.model.BuyTicketModel;
import com.melihcan.rabbitmq.model.CreateModel;
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


    public UserProfileService(IUserProfileRepository userProfileRepository, JwtTokenManager jwtTokenManager,
                              BuyTicketProducer buyTicketProducer){
        super(userProfileRepository);
        this.userProfileRepository=userProfileRepository;
        this.jwtTokenManager=jwtTokenManager;
        this.buyTicketProducer = buyTicketProducer;

    }



    public void save(CreateModel createModel){
        UserProfile userProfile = IUserProfileMapper.INSTANCE.toUserProfile(createModel);
        save(userProfile);
    }

    public Boolean updateDto(UpdateUserRequestDto updateUserRequestDto) {
        Optional<Long>authId =  jwtTokenManager.getIdFromToken(updateUserRequestDto.getToken());
        System.out.println(authId);
        if (authId.isEmpty()) throw new UserException(EErrorType.INVALID_TOKEN);
        UserProfile userProfile = userProfileRepository.findOptionalByAuthid(authId.get()).get();
        userProfile.setAddress(updateUserRequestDto.getAddress());
        userProfile.setPhone(updateUserRequestDto.getPhone());
        userProfile.setEmail(updateUserRequestDto.getEmail());
        userProfile.setAddress(updateUserRequestDto.getAddress());
        userProfile.setUsername(userProfile.getUsername());
        update(userProfile);
        return true;
    }

    public Boolean buyTicket(BuyTicketRequestDto buyTicketRequestDto) {
        Optional<Long>userId = jwtTokenManager.getIdFromToken(buyTicketRequestDto.getToken());
        if (userId.isEmpty())throw new UserException(EErrorType.INVALID_TOKEN);
        BuyTicketModel buyTicketModel = new BuyTicketModel();
        buyTicketModel.setUserid(userId.get());

        buyTicketModel.setAmount(buyTicketRequestDto.getAmount());
        buyTicketModel.setTitle(buyTicketRequestDto.getTitle());
        System.out.println(buyTicketModel + " BURADAYIZ");
        buyTicketProducer.createSendMessage(buyTicketModel);
        return true;

    }


}
