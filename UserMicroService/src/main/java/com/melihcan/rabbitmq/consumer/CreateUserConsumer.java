package com.melihcan.rabbitmq.consumer;

import com.melihcan.rabbitmq.model.CreateModel;
import com.melihcan.service.UserProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateUserConsumer {

    private final UserProfileService userProfileService;

    @RabbitListener(queues = "queue-auth-create-user")
    public void createUserConsumerListener(CreateModel createModel){
        System.out.println("Gelen mesaj... : " + createModel.toString());
        userProfileService.save(createModel);
    }
}
