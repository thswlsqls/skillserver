package com.ebson.skillserver.repository;

import com.ebson.skillserver.domain.TestRedis;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Slf4j
@RequiredArgsConstructor
public class TestRedisRepositoryTemp {

    private RedisOperations<String, Object> redisOperations;

    public TestRedisRepositoryTemp(RedisOperations<String, Object> redisOperations) {
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
    }

    public  void delete(String id) {
        redisOperations.delete(id);
    }
}
