package com.squirrel.rabbitmq.rabbit.multihello;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class MHSender2 {

    @Autowired
    private AmqpTemplate rabbitTemplate;

    public void send(int i) {
        String context = "multi hello queue " + i;
        System.out.println("Sender2: " + context);
        this.rabbitTemplate.convertAndSend("MH", context);
    }
}
