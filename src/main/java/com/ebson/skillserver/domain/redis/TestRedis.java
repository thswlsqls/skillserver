package com.ebson.skillserver.domain.redis;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.util.UUID;


@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@RedisHash("testRedis") // Key prefix
public class TestRedis {

    @Id // Key
    private UUID testRedisId;
    private int no;
    private String createUser;

}
