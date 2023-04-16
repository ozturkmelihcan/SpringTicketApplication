package com.melihcan.rabbitmq.consumer;

import com.melihcan.rabbitmq.model.CreateModel;
import com.melihcan.service.OrganizationService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateOrganizationConsumer {

    private final OrganizationService organizationService;


    @RabbitListener(queues = "queue-auth-create-organization")
    public void createOrganizationConsumerListener(CreateModel createModel){
        organizationService.save(createModel);
    }

}
