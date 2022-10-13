package com.example.demo.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQSenderConfig {

    private String nameMovieQueue="movie-queue";

    @Bean
    public Queue queueMovie(){
        return new Queue(this.nameMovieQueue,true);
    }
}
