package com.ebson.skillserver.repository;

import com.ebson.skillserver.domain.TestUser;
import jakarta.persistence.EntityManager;
import jakarta.persistence.FlushModeType;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

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

    @Test
    @DisplayName("testUserJPARepository.findById(userId)")
    public void findById(){
        UUID userId = UUID.fromString("c3c1f9ce-4994-11ee-9419-0a0df06f9f06");
        Optional<TestUser> testUser = testUserJPARepository.findById(userId);
        Assertions.assertEquals(userId, testUser.get().getUserId());
    }

    @Test
    @DisplayName("testUserJPARepository.existsById(userId)")
    public void existsById(){
        UUID userId = UUID.fromString("c3c1f9ce-4994-11ee-9419-0a0df06f9f06");
        Assertions.assertTrue(testUserJPARepository.existsById(userId));
    }

    @Test
    @DisplayName("testUserJPARepository.findAll()")
    public void findAll(){
        List<TestUser> testUserList = testUserJPARepository.findAll();
        Assertions.assertTrue(testUserList != null);
    }

    @Test
    @DisplayName("testUserJPARepository.findAllById(userIdList)")
    public void findAllById(){
        UUID userId_1 = UUID.fromString("c3c1f9ce-4994-11ee-9419-0a0df06f9f06");
        UUID userId_2 = UUID.fromString("28561783-4a44-11ee-9419-0a0df06f9f06");
        List<UUID> userIdList = new ArrayList<>();
        userIdList.add(userId_1);
        userIdList.add(userId_2);
        List<TestUser> testUserList = testUserJPARepository.findAllById(userIdList);
        Assertions.assertEquals(2, testUserList.size());
    }

    @Test
    @DisplayName("testUserJPARepository.deleteById(testUserId)")
    public void deleteById(){
        TestUser testUser = new TestUser();
        UUID testUserId = UUID.randomUUID();
        testUser.setUserId(testUserId);
        testUser.setName("test");
        testUser.setCreateUser("system");
        testUser.setLastUpdateUser("system");
        testUserRepository.save_custom(testUser);
        testUserJPARepository.deleteById(testUserId);
        Assertions.assertFalse(testUserJPARepository.existsById(testUserId));
    }

    @Test
    @DisplayName("testUserJPARepository.delete(testUser)")
    public void delete(){
        TestUser testUser = new TestUser();
        UUID testUserId = UUID.randomUUID();
        testUser.setUserId(testUserId);
        testUser.setName("test");
        testUser.setCreateUser("system");
        testUser.setLastUpdateUser("system");
        testUserRepository.save_custom(testUser);
        testUserJPARepository.delete(testUser);
        Assertions.assertFalse(testUserJPARepository.existsById(testUserId));
    }

    @Test
    @DisplayName("testUserJPARepository.deleteAllById(userIdList)")
    public void deleteAllById(){
        TestUser testUser_1 = new TestUser();
        TestUser testUser_2 = new TestUser();
        TestUser testUser_3 = new TestUser();
        UUID testUserId_1 = UUID.randomUUID();
        UUID testUserId_2 = UUID.randomUUID();
        UUID testUserId_3 = UUID.randomUUID();

        List<UUID> userIdList = new ArrayList<>();
        userIdList.add(testUserId_1); userIdList.add(testUserId_2); userIdList.add(testUserId_3);

        testUser_1.setUserId(testUserId_1); testUser_1.setName("test"); testUser_1.setCreateUser("system"); testUser_1.setLastUpdateUser("system");
        testUser_2.setUserId(testUserId_2); testUser_2.setName("test"); testUser_2.setCreateUser("system"); testUser_2.setLastUpdateUser("system");
        testUser_3.setUserId(testUserId_3); testUser_3.setName("test"); testUser_3.setCreateUser("system"); testUser_3.setLastUpdateUser("system");

        testUserRepository.save_custom(testUser_1);
        testUserRepository.save_custom(testUser_2);
        testUserRepository.save_custom(testUser_3);
        testUserJPARepository.deleteAllById(userIdList);
        Assertions.assertTrue(testUserJPARepository.findAllById(userIdList).isEmpty());
    }

    @Test
    @DisplayName("testUserJPARepository.deleteAllById(userIdList)")
    public void deleteAll(){
        TestUser testUser_1 = new TestUser();
        TestUser testUser_2 = new TestUser();
        TestUser testUser_3 = new TestUser();
        UUID testUserId_1 = UUID.randomUUID();
        UUID testUserId_2 = UUID.randomUUID();
        UUID testUserId_3 = UUID.randomUUID();
        List<TestUser> testUserList = new ArrayList<>();
        testUserList.add(testUser_1); testUserList.add(testUser_2); testUserList.add(testUser_3);

        List<UUID> userIdList = new ArrayList<>();
        userIdList.add(testUserId_1); userIdList.add(testUserId_2); userIdList.add(testUserId_3);

        testUser_1.setUserId(testUserId_1); testUser_1.setName("test"); testUser_1.setCreateUser("system"); testUser_1.setLastUpdateUser("system");
        testUser_2.setUserId(testUserId_2); testUser_2.setName("test"); testUser_2.setCreateUser("system"); testUser_2.setLastUpdateUser("system");
        testUser_3.setUserId(testUserId_3); testUser_3.setName("test"); testUser_3.setCreateUser("system"); testUser_3.setLastUpdateUser("system");

        testUserRepository.save_custom(testUser_1);
        testUserRepository.save_custom(testUser_2);
        testUserRepository.save_custom(testUser_3);
        testUserJPARepository.deleteAll(testUserList);
        Assertions.assertTrue(testUserJPARepository.findAllById(userIdList).isEmpty());
    }

    @Autowired
    private EntityManager em;

    @Test
    @DisplayName("testUserJPARepository.flush()")
    @Transactional
    public void flush(){
        System.out.println("em.getFlushMode()" + em.getFlushMode());
        List<TestUser> testUserList = testUserJPARepository.findAll();
        testUserJPARepository.deleteAll();
        testUserJPARepository.flush();
        Assertions.assertNotNull(testUserJPARepository.saveAll(testUserList));
    }

//    /**
//     * Flushes all pending changes to the database.
//     */
//    void flush();
//
//    /**
//     * Saves an entity and flushes changes instantly.
//     *
//     * @param entity entity to be saved. Must not be {@literal null}.
//     * @return the saved entity
//     */
//    <S extends T> S saveAndFlush(S entity);
//
//    /**
//     * Saves all entities and flushes changes instantly.
//     *
//     * @param entities entities to be saved. Must not be {@literal null}.
//     * @return the saved entities
//     * @since 2.5
//     */
//    <S extends T> List<S> saveAllAndFlush(Iterable<S> entities);
//
//    /**
//     * Deletes the given entities in a batch which means it will create a single query. This kind of operation leaves JPAs
//     * first level cache and the database out of sync. Consider flushing the {@link EntityManager} before calling this
//     * method.
//     *
//     * @param entities entities to be deleted. Must not be {@literal null}.
//     * @deprecated Use {@link #deleteAllInBatch(Iterable)} instead.
//     */
//    @Deprecated
//    default void deleteInBatch(Iterable<T> entities) {
//        deleteAllInBatch(entities);
//    }
//
//    /**
//     * Deletes the given entities in a batch which means it will create a single query. This kind of operation leaves JPAs
//     * first level cache and the database out of sync. Consider flushing the {@link EntityManager} before calling this
//     * method.
//     *
//     * @param entities entities to be deleted. Must not be {@literal null}.
//     * @since 2.5
//     */
//    void deleteAllInBatch(Iterable<T> entities);
//
//    /**
//     * Deletes the entities identified by the given ids using a single query. This kind of operation leaves JPAs first
//     * level cache and the database out of sync. Consider flushing the {@link EntityManager} before calling this method.
//     *
//     * @param ids the ids of the entities to be deleted. Must not be {@literal null}.
//     * @since 2.5
//     */
//    void deleteAllByIdInBatch(Iterable<ID> ids);
//
//    /**
//     * Deletes all entities in a batch call.
//     */
//    void deleteAllInBatch();
//
//    /**
//     * Returns a reference to the entity with the given identifier. Depending on how the JPA persistence provider is
//     * implemented this is very likely to always return an instance and throw an
//     * {@link jakarta.persistence.EntityNotFoundException} on first access. Some of them will reject invalid identifiers
//     * immediately.
//     *
//     * @param id must not be {@literal null}.
//     * @return a reference to the entity with the given identifier.
//     * @see EntityManager#getReference(Class, Object) for details on when an exception is thrown.
//     * @deprecated use {@link JpaRepository#getReferenceById(ID)} instead.
//     */
//    @Deprecated
//    T getOne(ID id);
//
//    /**
//     * Returns a reference to the entity with the given identifier. Depending on how the JPA persistence provider is
//     * implemented this is very likely to always return an instance and throw an
//     * {@link jakarta.persistence.EntityNotFoundException} on first access. Some of them will reject invalid identifiers
//     * immediately.
//     *
//     * @param id must not be {@literal null}.
//     * @return a reference to the entity with the given identifier.
//     * @see EntityManager#getReference(Class, Object) for details on when an exception is thrown.
//     * @deprecated use {@link JpaRepository#getReferenceById(ID)} instead.
//     * @since 2.5
//     */
//    @Deprecated
//    T getById(ID id);
//
//    /**
//     * Returns a reference to the entity with the given identifier. Depending on how the JPA persistence provider is
//     * implemented this is very likely to always return an instance and throw an
//     * {@link jakarta.persistence.EntityNotFoundException} on first access. Some of them will reject invalid identifiers
//     * immediately.
//     *
//     * @param id must not be {@literal null}.
//     * @return a reference to the entity with the given identifier.
//     * @see EntityManager#getReference(Class, Object) for details on when an exception is thrown.
//     * @since 2.7
//     */
//    T getReferenceById(ID id);
//
//    /*
//     * (non-Javadoc)
//     * @see org.springframework.data.repository.query.QueryByExampleExecutor#findAll(org.springframework.data.domain.Example)
//     */
//    @Override
//    <S extends T> List<S> findAll(Example<S> example);
//
//    @Override
//    <S extends T> List<S> findAll(Example<S> example, Sort sort);

}