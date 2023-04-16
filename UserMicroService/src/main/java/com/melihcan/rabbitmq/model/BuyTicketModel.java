package com.melihcan.rabbitmq.model;

import lombok.*;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@ToString
public class BuyTicketModel implements Serializable {

    Long userid;

   Integer amount;

   String title;
}
