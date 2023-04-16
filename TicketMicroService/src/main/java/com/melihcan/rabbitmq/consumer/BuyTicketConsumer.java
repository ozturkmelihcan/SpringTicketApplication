package com.melihcan.rabbitmq.consumer;

import com.melihcan.rabbitmq.model.BuyTicketModel;
import com.melihcan.service.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BuyTicketConsumer {

    private final TicketService ticketService;

    @RabbitListener(queues = "queue-buy-ticket")
    public void  buyTicketConsumerListener(BuyTicketModel buyTicketModel){
        System.out.println(buyTicketModel);
        ticketService.buyTicket(buyTicketModel);
    }
}
