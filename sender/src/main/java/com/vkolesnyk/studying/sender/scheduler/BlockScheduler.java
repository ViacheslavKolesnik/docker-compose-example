package com.vkolesnyk.studying.sender.scheduler;

import com.google.gson.Gson;
import com.vkolesnyk.studying.sender.generator.BlockGenerator;
import com.vkolesnyk.studying.sender.sender.RabbitSender;
import lombok.extern.log4j.Log4j2;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Log4j2
@Component
public final class BlockScheduler {

    private final Gson gson;
    private final RabbitSender rabbitSender;

    public BlockScheduler(Gson gson, RabbitSender rabbitSender) {
        this.rabbitSender = rabbitSender;
        this.gson = gson;
    }

    @Scheduled(initialDelay = 5_000, fixedDelayString = "${block-rate}")
    public void sendBlock() {
        var block = BlockGenerator.generate();
        log.info("Generated block: {}", block);
        rabbitSender.send(gson.toJson(block));
        log.info("Sent block to rabbitmq: {}", block);
    }

}
