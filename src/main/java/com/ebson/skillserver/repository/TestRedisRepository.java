package com.ebson.skillserver.repository;

import com.ebson.skillserver.domain.redis.TestRedis;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TestRedisRepository extends CrudRepository<TestRedis, UUID> {
}
