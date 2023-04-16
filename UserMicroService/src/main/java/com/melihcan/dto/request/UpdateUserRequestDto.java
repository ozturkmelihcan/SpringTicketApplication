package com.melihcan.dto.request;

import com.melihcan.repository.entity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.validation.constraints.Email;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class UpdateUserRequestDto {

    String token;

    String phone;

    String address;

    String about;
    @Email
    String email;


}
