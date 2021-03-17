package com.vkolesnyk.studying.receiver.configuration;

import com.google.gson.Gson;
import com.vkolesnyk.studying.receiver.dto.RabbitConfig;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

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
    public RedisTemplate<String, Long> redisTemplate(RedisConnectionFactory connectionFactory) {
        RedisTemplate<String, Long> template = new RedisTemplate<>();
        template.setConnectionFactory(connectionFactory);
        // Add some specific configuration here. Key serializers, etc.
        return template;
    }
}
