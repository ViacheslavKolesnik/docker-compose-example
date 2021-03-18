package com.vkolesnyk.studying.sender.scheduler;

import com.google.gson.Gson;
import com.vkolesnyk.studying.sender.ethereum.BlockProvider;
import com.vkolesnyk.studying.sender.sender.RabbitSender;
import lombok.extern.log4j.Log4j2;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Log4j2
@Component
public final class BlockScheduler {

    private final Gson gson;
    private final RabbitSender rabbitSender;
    private final BlockProvider blockProvider;

    public BlockScheduler(Gson gson, RabbitSender rabbitSender, BlockProvider blockProvider) {
        this.rabbitSender = rabbitSender;
        this.gson = gson;
        this.blockProvider = blockProvider;
    }

    @Scheduled(initialDelay = 5_000, fixedDelayString = "${block-rate}")
    public void sendBlock() {
        var block = blockProvider.getBlock();
        log.info("Got block from ethereum: {}", block);
        rabbitSender.send(gson.toJson(block));
        log.info("Sent block to rabbitmq: {}", block);
    }

}
