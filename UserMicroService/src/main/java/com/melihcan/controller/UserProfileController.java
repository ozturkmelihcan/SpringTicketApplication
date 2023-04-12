package com.melihcan.controller;

import com.melihcan.dto.request.BuyTicketRequestDto;
import com.melihcan.dto.request.UpdateUserRequestDto;
import com.melihcan.service.UserProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/userprofile")
public class UserProfileController {


    private final UserProfileService userProfileService;

    @PostMapping("/updateuser")
    public ResponseEntity<Boolean>updateUser(@RequestBody UpdateUserRequestDto dto){
        return ResponseEntity.ok(userProfileService.updateDto(dto));
    }

    @PostMapping("/buyticket")
    public ResponseEntity<Boolean>buyTicket(@RequestBody BuyTicketRequestDto buyTicketRequestDto){
        return ResponseEntity.ok(userProfileService.buyTicket(buyTicketRequestDto));
    }

}
