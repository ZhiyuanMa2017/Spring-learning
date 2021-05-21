package com.squirrel.rabbitmq.rabbit.multihello;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "MH")
public class MHReceiver {

    @RabbitHandler
    public void process(String mh) {
        System.out.println("Receiver1: " + mh);
    }
}
