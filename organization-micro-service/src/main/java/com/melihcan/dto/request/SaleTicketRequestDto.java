package com.melihcan.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class SaleTicketRequestDto {

    String token;
    Integer amount;
    String date;
    String title;
    String rules;
    String location;
    Double price;
    String time;
}
