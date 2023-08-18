package com.ebson.skillserver.service;

import com.ebson.skillserver.domain.TestUser;
import com.ebson.skillserver.repository.TestRepository;
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
public class TestService {

    @Autowired
    private final TestRepository testRepository;

    @Transactional
    public int insertTestUser(TestUser tu){
            testRepository.save(tu);
            return 1;
    }

    //    @Transactional(readOnly = true)
    public TestUser findOne(Long userId){
        return testRepository.findOne(userId);
    }

//    @Transactional(readOnly = true) //JPA가 성능 최적화함 - 영속성 컨텍스트를 플러쉬 하지 않고 더티 체크를 생략, 데이터베이스에게 읽기 전용으로 리소스 절약할 것을 지시함
    public List<TestUser> findUsers(){
        return testRepository.findAll();
    }

    private void validateDuplicateMember(TestUser tu) {
        List<TestUser> findDomains = testRepository.findByName(tu.getName());
        if (!findDomains.isEmpty()){
            throw new IllegalStateException("이미 존재하는 도메인입니다.");
        }
    }

}
