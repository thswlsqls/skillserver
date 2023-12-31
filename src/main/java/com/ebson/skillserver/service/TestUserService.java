package com.ebson.skillserver.service;

import com.ebson.skillserver.domain.TestUser;
import com.ebson.skillserver.repository.TestUserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class TestUserService {

    @Autowired
    private final TestUserRepository testUserRepository;

    @Transactional
    public int createTestUser(TestUser tu){
            testUserRepository.save(tu);
            return 1;
    }

    public List<TestUser> retrieveAllTestUsers(){
        return testUserRepository.findAll();
    }

    public TestUser findOne(String userId){
        return testUserRepository.findOne(userId);
    }

    private void validateDuplicateTestUser(TestUser tu) {
        TestUser findedTestUser =  testUserRepository.findOne(tu.getUserId().toString().replace("-", "")); // testUserRepository.findByName(tu.getName());
        if (findedTestUser != null){
            throw new IllegalStateException("이미 존재하는 도메인입니다.");
        }
    }

}
