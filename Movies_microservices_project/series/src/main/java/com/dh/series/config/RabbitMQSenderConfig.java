package com.dh.series.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQSenderConfig {

    private String serieQueue="serie-queue";

    @Bean
    public Queue queue(){
        return new Queue(this.serieQueue,true);
    }
}
