package com.ebson.skillserver.repository;

import com.ebson.skillserver.domain.TestUser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@SpringBootTest
public class TestUserRepositoryTest {

    @Autowired
    TestUserRepository testUserRepository;

    @Test
    @Transactional
    @DisplayName("유일한 name 으로 test_user를 insert하고 1개 로우가 조회되는지 테스트")
    public void saveTest(){
        TestUser tu = new TestUser();
        String testName = UUID.randomUUID().toString();
        tu.setName(testName);
        tu.setCreateUser("system");
        tu.setLastUpdateUser("system");
        testUserRepository.save(tu);
        Assertions.assertEquals(1, testUserRepository.findByName(testName).size());
    }

    @Test
    @DisplayName("test_user 테이블에서 user_id으로 조회가능한지 테스트")
    public void findOneTest(){
        String findUserId = "0dbff0ce8d83441cad642f2d8f587ab8";
        TestUser findedTestUser = testUserRepository.findOne(findUserId);
        Assertions.assertEquals(findUserId, findedTestUser.getUserId().toString().replace("-", ""));
    }

    @Test
    @DisplayName("test_user 테이블의 모든 로우들을 조회하고 개수를 테스트")
    public void findAllTest(){
        Assertions.assertEquals(5, testUserRepository.findAll().size());
    }

    @Test
    @DisplayName("testUserRepository.deleteAllByName()")
    public void deleteByName(){
        Assertions.assertEquals(1, testUserRepository.deleteAllByName("테스트유저1"));
    }

}
