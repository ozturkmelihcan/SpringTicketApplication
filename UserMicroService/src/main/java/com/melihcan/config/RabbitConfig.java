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
    private String exchangeDirectAuth = "exchange-direct-auth";

    private String exchangeDirectBuyTicket = "exchange-buy-ticket";
    // KEY

    private String keyAuth = "key-auth";

    private String keyBuyTicket = "key-buy-ticket";

    // QUEUE

    private String queueAuthCreateUser = "queue-auth-create-user";

    private String queueBuyTicket = "queue-buy-ticket";



    // exchange

    @Bean
    DirectExchange directExchangeAuth(){
        return new DirectExchange(exchangeDirectAuth);
    }

    @Bean
    DirectExchange directExchangeBuyTicket(){
        return new DirectExchange(exchangeDirectBuyTicket);
    }

    // kuyruk

    @Bean
    Queue createAuthUserQueue(){
        return new Queue(queueAuthCreateUser);
    }


    @Bean
    Queue buyTicketQueue(){
        return new Queue(queueBuyTicket);
    }

    // binding


    @Bean
    public Binding bindingCreateAuthUser(final Queue createAuthUserQueue,final DirectExchange directExchangeAuth){
        return BindingBuilder.bind(createAuthUserQueue).to(directExchangeAuth).with(keyAuth);
    }

    @Bean
    public Binding bindingBuyTicket(final Queue buyTicketQueue,final DirectExchange directExchangeBuyTicket){
        return BindingBuilder.bind(buyTicketQueue).to(directExchangeBuyTicket).with(keyBuyTicket);
    }

}
