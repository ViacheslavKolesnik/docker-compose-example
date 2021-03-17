package com.vkolesnyk.studying.sender.sender;

import com.vkolesnyk.studying.sender.dto.RabbitConfig;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
public class RabbitSender {
    private final RabbitTemplate rabbitTemplate;
    private final RabbitConfig rabbitConfig;

    public RabbitSender(RabbitTemplate rabbitTemplate, RabbitConfig rabbitConfig) {
        this.rabbitTemplate = rabbitTemplate;
        this.rabbitConfig = rabbitConfig;
    }

    public void send(String message) {
        rabbitTemplate.convertAndSend(rabbitConfig.getQueueName(), message);
    }
}
