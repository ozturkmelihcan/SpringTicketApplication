package com.melihcan.controller;

import com.melihcan.dto.request.SaleTicketRequestDto;
import com.melihcan.dto.request.UpdateOrganizationRequestDto;
import com.melihcan.service.OrganizationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import static com.melihcan.constant.RestEndPoints.*;
@RestController
@RequiredArgsConstructor
@RequestMapping(ORGANIZATION)
public class OrganizationController {

    private final OrganizationService organizationService;


    @PostMapping("/updateorganization")
    public ResponseEntity<Boolean>updateOrganization(@RequestBody UpdateOrganizationRequestDto updateOrganizationRequestDto){
        return ResponseEntity.ok(organizationService.updateDto(updateOrganizationRequestDto));
    }


    // bilet olustur ve sat

    @PostMapping("/saleticket")
    public ResponseEntity<Boolean>saleticket(@RequestBody SaleTicketRequestDto saleTicketRequestDto){
        return ResponseEntity.ok(organizationService.saleTicketDto(saleTicketRequestDto));
    }



}
