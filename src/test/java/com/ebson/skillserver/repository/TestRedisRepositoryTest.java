package com.ebson.skillserver.repository;

import com.ebson.skillserver.domain.TestRedis;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

@SpringBootTestgit
public class TestRedisRepositoryTest {

    @Autowired
    TestRedisRepository testRedisRepository;

    @Test
    @DisplayName("saveTestRedis 메서드 호출 결과 정상인지 테스트")
    public void saveTest(){
        TestRedis testRedis = new TestRedis();
        UUID redisId = UUID.fromString("2344da40-4a01-4369-a0ed-28d48b51509b");
        testRedis.setTestRedisId(redisId);
        testRedis.setNo(1);
        testRedis.setCreateUser("system");
        TestRedis returnTestRedis = testRedisRepository.save(testRedis);
        Assertions.assertEquals(UUID.fromString("2344da40-4a01-4369-a0ed-28d48b51509b"), returnTestRedis.getTestRedisId());
    }

}

