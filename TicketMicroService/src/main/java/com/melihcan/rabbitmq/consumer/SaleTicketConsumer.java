package com.melihcan.rabbitmq.consumer;

import com.melihcan.rabbitmq.model.SaleTicketModel;
import com.melihcan.service.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SaleTicketConsumer {

    private final TicketService ticketService;

    @RabbitListener(queues = "queue-sale-ticket")
    public void createTicketConsumerListener(SaleTicketModel saleTicketModel){
        ticketService.createTicket(saleTicketModel);
    }

}
