package com.vkolesnyk.studying.sender.configuration;

import com.google.gson.Gson;
import com.vkolesnyk.studying.sender.dto.RabbitConfig;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Collections;

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

    @Bean
    public WebClient webClient() {
        return WebClient.builder()
                .baseUrl("https://api.blockcypher.com/v1/beth/test")
                .defaultCookie("cookieKey", "cookieValue")
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .defaultUriVariables(Collections.singletonMap("url", "https://api.blockcypher.com/v1/beth/test"))
                .build();
    }
}
