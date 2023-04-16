package com.melihcan.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

    // ---------------- exchange section ------------------
    private String exchangeDirectAuth = "exchange-direct-auth";

    // ----------- key section ------------------
    private String keyUser = "key-user";

    private String keyOrganization = "key-organization";

    // ----------------- queue section ----------------------
    private String queueAuthCreateUser = "queue-auth-create-user";

    private String queueAuthCreateOrganization = "queue-auth-create-organization";

    // -----------------------------------DIRECTEXCHANGE BEAN------------------------------------
    @Bean
    DirectExchange directExchangeUser(){
        return new DirectExchange(exchangeDirectAuth);
    }

    @Bean
    DirectExchange directExchangeOrganization(){
        return new DirectExchange(exchangeDirectAuth);
    }

    // -----------------------------------QUEUE BEAN------------------------------------
    @Bean
    Queue createAuthUserQueue(){
        return new Queue(queueAuthCreateUser);
    }

    @Bean
    Queue createAuthOrganizationQueue(){
        return new Queue(queueAuthCreateOrganization);
    }


    // -----------------------------------BINDING BEAN------------------------------------
    @Bean
    public Binding bindingCreateAuthUser(final Queue createAuthUserQueue,final DirectExchange directExchangeUser){
        return BindingBuilder.bind(createAuthUserQueue).to(directExchangeUser).with(keyUser);
    }

    @Bean
    public Binding bindingCreateAuthOrganization(final Queue createAuthOrganizationQueue,final DirectExchange directExchangeOrganization){
        return BindingBuilder.bind(createAuthOrganizationQueue).to(directExchangeOrganization).with(keyOrganization);
    }
}
