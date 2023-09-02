package com.ebson.skillserver.repository;

import com.ebson.skillserver.domain.TestUser;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class TestUserJPARepositoryTest {

    @Autowired
    TestUserJPARepository testUserJPARepository;

    @Autowired
    TestUserRepository testUserRepository;

    @BeforeEach
    public void beforeEach(){
        testUserRepository.deleteAllByName("JPA테스트유저");
    }

    @AfterEach
    public void afterEach(){
        testUserRepository.deleteAllByName("JPA테스트유저");
    }

    @Test
    @DisplayName("testUserJPARepository.save(tu)")
    public void save(){
        long before = testUserJPARepository.count();
        TestUser tu = new TestUser();
        tu.setName("JPA테스트유저");
        tu.setCreateUser("system");
        tu.setLastUpdateUser("system");
        testUserJPARepository.save(tu);
        Assertions.assertEquals(before+1, testUserJPARepository.count());
    }

    @Test
    @DisplayName("testUserJPARepository.saveAll(tuList)")
    public void saveAll(){
        long before = testUserJPARepository.count();
        TestUser tu1 = new TestUser();
        tu1.setName("JPA테스트유저");
        tu1.setCreateUser("system");
        tu1.setLastUpdateUser("system");
        TestUser tu2 = new TestUser();
        tu2.setName("JPA테스트유저");
        tu2.setCreateUser("system");
        tu2.setLastUpdateUser("system");
        List<TestUser> tuList = new ArrayList<>();
        tuList.add(tu1); tuList.add(tu2);
        testUserJPARepository.saveAll(tuList);
        Assertions.assertEquals(before+2, testUserJPARepository.count());
    }

//    @Test
//    @DisplayName("testUserJPARepository.deleteAll()")
//    public void deleteAll(){
//        testUserJPARepository.deleteAll();
//        Assertions.assertEquals(0, testUserJPARepository.count());
//    }

}
