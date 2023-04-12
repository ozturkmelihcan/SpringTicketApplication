package com.melihcan.rabbitmq.producer;

import com.melihcan.rabbitmq.model.BuyTicketModel;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BuyTicketProducer {

    private final RabbitTemplate rabbitTemplate;

    public void createSendMessage(BuyTicketModel buyTicketModel){
        rabbitTemplate.convertAndSend("exchange-direct-ticket","key-ticket",buyTicketModel);
    }
}
