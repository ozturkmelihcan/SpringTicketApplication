package com.melihcan.rabbitmq.consumer;

import com.melihcan.rabbitmq.model.CreateUserModel;
import com.melihcan.service.UserProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateUserConsumer {

    private final UserProfileService userProfileService;

    @RabbitListener(queues = "queue-auth-create-user")
    public void createUserConsumerListener(CreateUserModel createUserModel){
        System.out.println("Gelen mesaj... : " + createUserModel.toString());
        userProfileService.save(createUserModel);
    }
}
