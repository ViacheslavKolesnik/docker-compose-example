package com.vkolesnyk.studying.sender.configuration;

import com.google.gson.Gson;
import com.vkolesnyk.studying.sender.dto.RabbitConfig;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfiguration {

    @Value("${rabbitmq-queue-name}")
    private String queueName;

    @Bean
    public Queue rabbitMQQueue() {
        return new Queue(queueName, true);
    }

    @Bean
    public RabbitConfig rabbitConfig() {
        return new RabbitConfig(queueName);
    }

    @Bean
    public Gson gson() {
        return new Gson();
    }
}
