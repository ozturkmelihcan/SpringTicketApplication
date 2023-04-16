package com.melihcan.rabbitmq.model;


import lombok.*;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@ToString
public class SaleTicketModel implements Serializable {

    String organizationid;
    Integer amount;
    String date;
    String title;
    String rules;
    String location;
    Double price;
    String time;
}
