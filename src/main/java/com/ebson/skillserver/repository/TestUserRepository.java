package com.ebson.skillserver.repository;

import com.ebson.skillserver.domain.TestUser;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
@Slf4j
@RequiredArgsConstructor
public class TestUserRepository {

    private final EntityManager em;

    public void save(TestUser tu){
        em.persist(tu);
    }

    public List<TestUser> findAll() {
        return em.createQuery("SELECT tu " +
                                      "FROM TestUser tu " +
                                      "ORDER BY lastUpdateDate DESC", TestUser.class)
                .getResultList(); //JPQL은 SQL로 번역되며 기능적으로는 동일하나, SQL과 달리 테이블이 아니라 엔티티 객체에 대해 질의함
    }

    public TestUser findOne(String userId) {
        return (TestUser) em.createNativeQuery("SELECT * " +
                                                        "FROM test_user tu " +
                                                        "WHERE tu.user_id = unhex(?)", TestUser.class)
                .setParameter(1, userId)
                .getSingleResult();
    }

    public List<TestUser> findByName(String name){
        return em.createQuery("SELECT tu " +
                                      "FROM TestUser tu " +
                                      "WHERE tu.name = :name " +
                                      "ORDER BY tu.lastUpdateDate DESC", TestUser.class)
                .setParameter("name", name) //JPQL에서 ":" 뒤의 문자는 파라미터 변수로 인식함
                .getResultList();
    }

}
