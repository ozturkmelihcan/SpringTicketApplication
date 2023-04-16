package com.melihcan.rabbitmq.producer;

import com.melihcan.rabbitmq.model.CreateModel;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateProducer {

    private final RabbitTemplate rabbitTemplate;

    public void createSendMessageToUser(CreateModel createModel){
        rabbitTemplate.convertAndSend("exchange-direct-auth","key-auth", createModel);
    }

    public void createSendMessageToOrganization(CreateModel createModel){
        rabbitTemplate.convertAndSend("exchange-direct-auth","key-organization",createModel);
    }

}
