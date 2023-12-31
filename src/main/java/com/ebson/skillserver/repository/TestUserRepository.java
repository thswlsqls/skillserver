package com.ebson.skillserver.repository;

import com.ebson.skillserver.domain.TestUser;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.FlushModeType;
import jakarta.transaction.Transaction;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
@Slf4j
@RequiredArgsConstructor
public class TestUserRepository {

    @Autowired
    private EntityManager em;

    @Transactional
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

    @Transactional
    public long deleteAllByName(String name){
        long rows = em.createQuery("DELETE FROM TestUser tu WHERE tu.name = :name")
                .setParameter("name", name)
                .executeUpdate();
        return rows;
    }

    @Transactional
    public int save_custom(TestUser testUser){
        UUID userId = testUser.getUserId();
        String name = testUser.getName();
        String createUser = testUser.getCreateUser();
        String lastUpdateUser = testUser.getLastUpdateUser();
        return em.createNativeQuery("INSERT INTO test_user" +
                                            "(" +
                                            "user_id" +
                                            ", name" +
                                            ", create_user" +
                                            ", last_update_user" +
                                            ")" +
                                            "VALUES" +
                                            "(" +
                                            "UNHEX(REPLACE(?, '-', ''))" +
                                            ", ?" +
                                            ", ?" +
                                            ", ?" +
                                            ")").setParameter(1, userId.toString())
                                            .setParameter(2, name)
                                            .setParameter(3, createUser)
                                            .setParameter(4, lastUpdateUser)
                                            .executeUpdate();
    }

    @Transactional
    public int deleteById_custom(UUID userId){
        return em.createNativeQuery("DELETE FROM test_user WHERE 1=1 AND user_id = UNHEX(REPLACE(LOWER(?), '-', ''))")
                .setParameter(1, userId.toString())
                .executeUpdate();
    }

}
