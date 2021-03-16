package com.vkolesnyk.studying.sender.generator;

import com.vkolesnyk.studying.sender.dto.Block;

import java.util.Random;

public final class BlockGenerator {

    private static final Random RANDOM = new Random();

    public static Block generate() {
        return Block.builder()
                .blockNumber(RANDOM.nextLong())
                .blockTime(RANDOM.nextLong())
                .build();
    }
}
