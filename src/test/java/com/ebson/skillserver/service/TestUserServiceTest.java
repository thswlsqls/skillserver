package com.ebson.skillserver.service;

import com.ebson.skillserver.domain.TestUser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@SpringBootTest
public class TestUserServiceTest {

    @Autowired
    TestUserService testUserService;

    @Test
    @Transactional
    @DisplayName("createTestUser 메서드 호출 결과 정상인지 테스트")
    public void createTestUserTest(){
        TestUser tu = new TestUser();
        String testName = UUID.randomUUID().toString();
        tu.setName(testName);
        tu.setCreateUser("system");
        tu.setLastUpdateUser("system");

        Assertions.assertEquals(1, testUserService.createTestUser(tu));
    }

    @Test
    @DisplayName("retrieveAllTestUsers 메서드 호출 결과 정상인지 테스트")
    public void retrieveAllTestUsersTest(){
        Assertions.assertEquals(3, testUserService.retrieveAllTestUsers().size());
    }

    @Test
    @DisplayName("findOne 메서드 호출 결과 정상인지 테스트")
    public void findOneTest(){
        String findUserId = "0dbff0ce8d83441cad642f2d8f587ab8";
        TestUser findTestUser = testUserService.findOne(findUserId);
        Assertions.assertEquals(findUserId, findTestUser.getUserId().toString().replace("-", ""));
    }

}
