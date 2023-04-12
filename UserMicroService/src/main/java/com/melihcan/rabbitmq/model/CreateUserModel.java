package com.melihcan.rabbitmq.model;

import lombok.*;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@ToString
public class CreateUserModel implements Serializable {

    Long authid;

    String username;

    String email;
}
