package com.melihcan.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class RabbitConfig {

    private String exchangeDirectTicket = "exchange-direct-ticket";
    private String exchangeDirectAuth = "exchange-direct-auth";

    private String keyTicket = "key-ticket";

    private String keyAuth = "key-auth";

    private String queueTicketBuy = "queue-ticket-buy";

    private String queueAuthCreateUser = "queue-auth-create-user";

    // exchange
    @Bean
    DirectExchange directExchange(){
        return new DirectExchange(exchangeDirectTicket);
    }

    @Bean
    DirectExchange directExchangeAuth(){
        return new DirectExchange(exchangeDirectAuth);
    }

    // kuyruk
    @Bean
    Queue salesTicketUserQueue(){
        return new Queue(queueTicketBuy);
    }

    @Bean
    Queue createAuthUserQueue(){
        return new Queue(queueAuthCreateUser);
    }

    // binding
    @Bean
    public Binding bindingSalesTicketUser(final Queue salesTicketUserQueue , final DirectExchange directExchange){
        return BindingBuilder.bind(salesTicketUserQueue).to(directExchange).with(keyTicket);
    }

    @Bean
    public Binding bindingCreateAuthUser(final Queue createAuthUserQueue,final DirectExchange directExchangeAuth){
        return BindingBuilder.bind(createAuthUserQueue).to(directExchangeAuth).with(keyAuth);
    }
}
