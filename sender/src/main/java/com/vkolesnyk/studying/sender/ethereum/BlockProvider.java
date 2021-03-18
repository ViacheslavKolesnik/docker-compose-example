package com.vkolesnyk.studying.sender.ethereum;

import com.vkolesnyk.studying.sender.dto.Block;
import com.vkolesnyk.studying.sender.dto.EthereumBlock;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

@Component
public class BlockProvider {
    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSSSSSSS'Z'");
    //"2021-03-18T20:37:39.540340358Z"

    private final WebClient webClient;

    public BlockProvider(WebClient webClient) {
        this.webClient = webClient;
    }

    public Block getBlock() {
        var ethBlock = webClient.get().retrieve().bodyToMono(EthereumBlock.class).block();
        var ethBlockTimeMillis = LocalDateTime.parse(ethBlock.getTime(), dateTimeFormatter)
                .toInstant(ZoneOffset.UTC)
                .toEpochMilli();
        return Block.builder()
                .blockNumber(ethBlock.getHeight())
                .blockTime(ethBlockTimeMillis)
                .build();
    }
}
