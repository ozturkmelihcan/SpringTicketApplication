package com.melihcan.rabbitmq.producer;

import com.melihcan.rabbitmq.model.SaleTicketModel;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SaleTicketProducer {

    private final RabbitTemplate rabbitTemplate;

    public void createSendMessage(SaleTicketModel saleTicketModel){
        rabbitTemplate.convertAndSend("exchange-direct-sale-ticket","key-sale", saleTicketModel);
    }
}
