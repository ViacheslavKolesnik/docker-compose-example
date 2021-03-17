package com.vkolesnyk.studying.receiver.repository;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class CounterRepository {
    public static final String COUNTER_REDIS_KEY = "block-counter";

    private final RedisTemplate<String, Long> redisTemplate;

    public CounterRepository(RedisTemplate<String, Long> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public Long incrementCounter() {
        return redisTemplate.opsForValue().increment(COUNTER_REDIS_KEY);
    }
}
