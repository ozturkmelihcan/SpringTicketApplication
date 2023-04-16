package com.melihcan.service;

import com.melihcan.dto.request.LoginRequestDto;
import com.melihcan.dto.request.RegisterRequestDto;
import com.melihcan.exception.AuthException;
import com.melihcan.exception.EErrorType;
import com.melihcan.mapper.IAuthMapper;
import com.melihcan.rabbitmq.model.CreateModel;
import com.melihcan.rabbitmq.producer.CreateProducer;
import com.melihcan.repository.IAuthRepository;
import com.melihcan.repository.entity.Auth;
import com.melihcan.repository.enums.ERole;
import com.melihcan.utility.JwtTokenManager;
import com.melihcan.utility.ServiceManager;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService extends ServiceManager<Auth,Long> {

    private final IAuthRepository authRepository;
    private final CreateProducer createProducer;

    private final JwtTokenManager jwtTokenManager;

    public AuthService(IAuthRepository authRepository, CreateProducer createProducer,
                       JwtTokenManager jwtTokenManager){
        super(authRepository);
        this.authRepository=authRepository;
        this.createProducer = createProducer;
        this.jwtTokenManager=jwtTokenManager;
    }

    public boolean register(RegisterRequestDto dto) {
        if (authRepository.isUsername(dto.getUsername())) throw new AuthException(EErrorType.AUTH_USERNAME_ERROR);
        Auth auth = save(IAuthMapper.INSTANCE.fromRegisterDto(dto));
        if (dto.getRoles().equals(ERole.USER)){
            createProducer.createSendMessageToUser(CreateModel.builder()
                    .authid(auth.getId())
                    .email(auth.getEmail())
                    .username(auth.getUsername())
                    .build());
        }else
            createProducer.createSendMessageToOrganization(CreateModel.builder()
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
