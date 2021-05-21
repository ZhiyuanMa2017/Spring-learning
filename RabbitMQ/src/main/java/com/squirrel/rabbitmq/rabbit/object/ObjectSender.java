package com.squirrel.rabbitmq.rabbit.object;

import com.squirrel.rabbitmq.model.User;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ObjectSender {

    @Autowired
    private AmqpTemplate rabbitTemplate;

    public void send(User user) {
        System.out.println("ObjectSender: " + user.toString());
        this.rabbitTemplate.convertAndSend("object", user);
    }
}
