package com.melihcan.rabbitmq.model;

import lombok.*;

import javax.persistence.Entity;
import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@ToString
public class CreateUser implements Serializable {

    Long authid;

    String username;

    String email;
}
