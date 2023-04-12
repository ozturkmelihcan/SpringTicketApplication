package com.melihcan.service;

import com.melihcan.dto.request.LoginRequestDto;
import com.melihcan.dto.request.RegisterRequestDto;
import com.melihcan.exception.AuthException;
import com.melihcan.exception.EErrorType;
import com.melihcan.mapper.IAuthMapper;
import com.melihcan.rabbitmq.model.CreateUser;
import com.melihcan.rabbitmq.producer.CreateUserProducer;
import com.melihcan.repository.IAuthRepository;
import com.melihcan.repository.entity.Auth;
import com.melihcan.utility.JwtTokenManager;
import com.melihcan.utility.ServiceManager;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService extends ServiceManager<Auth,Long> {

    private final IAuthRepository authRepository;
    private final CreateUserProducer createUserProducer;

    private final JwtTokenManager jwtTokenManager;

    public AuthService(IAuthRepository authRepository,CreateUserProducer createUserProducer,
                    JwtTokenManager jwtTokenManager){
        super(authRepository);
        this.authRepository=authRepository;
        this.createUserProducer=createUserProducer;
        this.jwtTokenManager=jwtTokenManager;
    }

    public boolean register(RegisterRequestDto dto) {
        if (authRepository.isUsername(dto.getUsername())) throw new AuthException(EErrorType.AUTH_USERNAME_ERROR);
        Auth auth = save(IAuthMapper.INSTANCE.fromRegisterDto(dto));
        createUserProducer.createSendMessage(CreateUser.builder()
                        .authid(auth.getId())
                        .email(auth.getEmail())
                        .username(auth.getUsername())
                .build());
        return true;
    }

    public String login(LoginRequestDto dto) {
        Optional<Auth>auth = authRepository.findOptionalByUsernameAndPassword(dto.getUsername(), dto.getPassword());
        if (auth.isEmpty()) throw new AuthException(EErrorType.AUTH_LOGIN_ERROR);
        Optional<String>token = jwtTokenManager.createToken(auth.get().getId());
        if (token.isEmpty()) throw new AuthException(EErrorType.INVALID_TOKEN);
        return token.get();
    }
}
