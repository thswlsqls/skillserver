package com.ebson.skillserver.config;

import com.ebson.skillserver.domain.TestUser;
import com.ebson.skillserver.domain.redis.TestRedis;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.UUID;

@SpringBootTest
public class RedisTemplateTest {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    private UUID testRedisId_1 = UUID.randomUUID();

    @Test
    @DisplayName("opsForHash test")
    public void opsForHashTest(){
        HashOperations<String, Object, Object> hashOperations = redisTemplate.opsForHash();
        hashOperations.put("key", "hello", "world");
        Assertions.assertTrue(true);
    }
}
