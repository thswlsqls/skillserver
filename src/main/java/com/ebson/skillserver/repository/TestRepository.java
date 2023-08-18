package com.ebson.skillserver.repository;

import com.ebson.skillserver.domain.TestDomain;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
@Slf4j
@RequiredArgsConstructor
public class TestRepository {

    private final EntityManager em;

    public void save(TestDomain td){
        log.info("TestRepository");
        em.persist(td);
    }

}
