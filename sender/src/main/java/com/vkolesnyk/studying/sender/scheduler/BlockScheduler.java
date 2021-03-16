package com.vkolesnyk.studying.sender.scheduler;

import com.google.gson.Gson;
import com.vkolesnyk.studying.sender.dto.RabbitConfig;
import com.vkolesnyk.studying.sender.generator.BlockGenerator;
import lombok.extern.log4j.Log4j2;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Log4j2
@Component
public final class BlockScheduler {

    private final RabbitTemplate rabbitTemplate;
    private final RabbitConfig rabbitConfig;
    private final Gson gson;

    public BlockScheduler(RabbitTemplate rabbitTemplate, RabbitConfig rabbitConfig, Gson gson) {
        this.rabbitTemplate = rabbitTemplate;
        this.rabbitConfig = rabbitConfig;
        this.gson = gson;
    }

    @Scheduled(initialDelay = 5_000, fixedDelay = 5_000)
    public void sendBlock() {
        var block = BlockGenerator.generate();
        log.info("Generated block: {}", block);
        var message = new Message(gson.toJson(block).getBytes(), new MessageProperties());
        rabbitTemplate.send(rabbitConfig.getQueueName(), message);
        log.info("Sent block to rabbitmq: {}", block);
    }

}
