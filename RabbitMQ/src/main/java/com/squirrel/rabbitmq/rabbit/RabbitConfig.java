package com.squirrel.rabbitmq.rabbit;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

    @Bean
    public Queue queue() {
        return new Queue("hello");
    }

    @Bean
    public Queue mhQueue() {
        return new Queue("MH");
    }

    @Bean
    public Queue objectQueue() {
        return new Queue("object");
    }
}
