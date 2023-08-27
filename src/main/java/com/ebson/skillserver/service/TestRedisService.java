package com.ebson.skillserver.service;

import com.ebson.skillserver.domain.redis.TestRedis;
import com.ebson.skillserver.repository.TestRedisRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Slf4j
@Service
@RequiredArgsConstructor
public class TestRedisService {

    @Autowired
    private final TestRedisRepository testRedisRepository;

    public TestRedis saveTestRedis(){

        return null;
    }

}
