package com.melihcan.controller;

import com.melihcan.dto.request.LoginRequestDto;
import com.melihcan.dto.request.RegisterRequestDto;
import com.melihcan.dto.response.LoginResponseDto;
import com.melihcan.exception.AuthException;
import com.melihcan.exception.EErrorType;
import com.melihcan.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<Boolean>register(@RequestBody RegisterRequestDto dto){
        if (!dto.getPassword().equals(dto.getRepassword()))
            throw new AuthException(EErrorType.AUTH_PASSWORD_ERROR);
        authService.register(dto);
        return ResponseEntity.ok(true);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@RequestBody LoginRequestDto dto){
        return ResponseEntity.ok(LoginResponseDto.builder()
                        .token(authService.login(dto))
                .build());
    }

}
