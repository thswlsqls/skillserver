package com.ebson.skillserver.service;

import com.ebson.skillserver.domain.TestDomain;
import com.ebson.skillserver.repository.TestRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class TestService {

    @Autowired
    private final TestRepository testRepository;

    @Transactional
    public int insertTestDomain(TestDomain td){
            log.info("TestService");
            testRepository.save(td);
            return 1;
    }

}
