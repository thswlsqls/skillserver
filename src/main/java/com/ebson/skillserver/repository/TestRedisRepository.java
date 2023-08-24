package com.ebson.skillserver.repository;

import com.ebson.skillserver.domain.TestRedis;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.ReactiveRedisOperations;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Slf4j
@RequiredArgsConstructor
public class TestRedisRepository {

    private RedisOperations<String, Object> redisOperations;

    public TestRedisRepository(RedisOperations<String, Object> redisOperations) {
        this.redisOperations = redisOperations;
    }

    public void save(TestRedis testRedis) {
        redisOperations.opsForValue().set(testRedis.getTestRedisId().toString(), testRedis);
    }

    public Object getTestRedisByTestRedisId(String testRedisId) {
        return redisOperations.opsForValue().get(testRedisId);
    }

    public List getAllTestRedis(String key) {
        return redisOperations.opsForList().range(key, 0, 2);
//        return redisOperations.keys("*")
//                .flatMap(redisOperations::opsForValue::get)
//                .cast(Dessert.class);
    }

    public  void delete(String id) {
        redisOperations.delete(id);
    }
}
