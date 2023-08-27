package com.ebson.skillserver.service;

import com.ebson.skillserver.domain.redis.TestRedis;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

@SpringBootTest
public class TestRedisServiceTest {

    @Autowired
    TestRedisService testRedisService;

    @Test
    @DisplayName("saveTestRedis 메서드 호출 결과 정상인지 테스트")
    public void saveTestRedisTest(){
        TestRedis returnTestRedis = testRedisService.saveTestRedis();
        Assertions.assertEquals(UUID.fromString("2344da40-4a01-4369-a0ed-28d48b51509b"), returnTestRedis.getTestRedisId());
    }

}
