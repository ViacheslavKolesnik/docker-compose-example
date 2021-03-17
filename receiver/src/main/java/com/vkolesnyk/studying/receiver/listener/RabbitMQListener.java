package com.vkolesnyk.studying.receiver.listener;

import com.google.gson.Gson;
import com.vkolesnyk.studying.receiver.dto.Block;
import com.vkolesnyk.studying.receiver.repository.BlockRepository;
import com.vkolesnyk.studying.receiver.repository.CounterRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Log4j2
@Component
public class RabbitMQListener {

    private final Gson gson;
    private final BlockRepository blockRepository;
    private final CounterRepository counterRepository;

    public RabbitMQListener(Gson gson, BlockRepository blockRepository, CounterRepository counterRepository) {
        this.gson = gson;
        this.blockRepository = blockRepository;
        this.counterRepository = counterRepository;
    }

    @RabbitListener(queues = "${rabbitmq-queue-name}")
    public void receive(String message) {
        log.info("Received message: {}", message);
        var block = gson.fromJson(message, Block.class);
        blockRepository.insertBlock(block);
        log.info("Saved block to repository: {}", block);
        var counter = counterRepository.incrementCounter();
        log.info("Incremented block counter. Current value is: {}", counter);
    }

}
