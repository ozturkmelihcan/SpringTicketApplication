package com.melihcan.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class RabbitConfig {

    // EXCHANGE

    private String exchangeDirectSaleTicket = "exchange-direct-sale-ticket";

    // KEY
    private String keySale = "key-sale";


    // QUEUE

    private String queueSaleTicket = "queue-sale-ticket";

    // exchange


    @Bean
    DirectExchange directExchangeSaleTicket(){
        return new DirectExchange(exchangeDirectSaleTicket);
    }

    // kuyruk


    @Bean
    Queue saleTicketOrganizationQueue(){
        return new Queue(queueSaleTicket);
    }

    // binding
    @Bean
    public Binding bindingSalesTicketUser(final Queue saleTicketOrganizationQueue , final DirectExchange directExchangeSaleTicket){
        return BindingBuilder.bind(saleTicketOrganizationQueue).to(directExchangeSaleTicket).with(keySale);
    }


}
